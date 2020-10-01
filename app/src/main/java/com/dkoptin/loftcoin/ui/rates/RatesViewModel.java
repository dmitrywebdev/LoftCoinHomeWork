package com.dkoptin.loftcoin.ui.rates;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.dkoptin.loftcoin.data.Coin;
import com.dkoptin.loftcoin.data.CoinsRepo;
import com.dkoptin.loftcoin.data.CurrencyRepo;

import java.util.List;

import javax.inject.Inject;

public class RatesViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();

    private final MutableLiveData<Boolean> forceRefresh = new MutableLiveData<>(true);

    private final LiveData<List<Coin>> coins;

    @Inject
    public RatesViewModel(CoinsRepo coinsRepo, CurrencyRepo currencyRepo) {

        LiveData<CoinsRepo.Query> query = Transformations.switchMap(forceRefresh, (r) -> {
         return Transformations.map(currencyRepo.currency(), (c) -> {
             isRefreshing.postValue(true);
            return CoinsRepo.Query.builder()
                    .currency(c.code())
                    .forceUpdate(r)
                    .build();
            });
        });
        LiveData<List<Coin>> coins = Transformations.switchMap(query, coinsRepo::listings);
        this.coins = Transformations.map(coins, (c) -> {
           isRefreshing.postValue(false);
           return c;
        });
    }

    @NonNull
    LiveData<List<Coin>> coins() {
        return coins;
    }

    @NonNull
    LiveData<Boolean> isRefreshing() {
        return isRefreshing;
    }

    final void refresh() {
       forceRefresh.postValue(true);
    }

}
