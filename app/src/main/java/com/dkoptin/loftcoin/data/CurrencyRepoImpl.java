package com.dkoptin.loftcoin.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.dkoptin.loftcoin.R;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRepoImpl implements CurrencyRepo {

    public final Context context;

    public CurrencyRepoImpl(@NonNull Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LiveData<List<Currency>> availableCurrencies() {
        return new AllCurrenciesLiveData(context);
    }

    @NonNull
    @Override
    public LiveData<Currency> currency() {
        return null;
    }

    @Override
    public void updateCurrency(@NonNull Currency currency) {

    }

    private static class AllCurrenciesLiveData extends LiveData<List<Currency>> {

        private Context context;

        AllCurrenciesLiveData(Context context) {
            this.context = context;
        }

        @Override
        protected void onActive() {
            List<Currency> currencies = new ArrayList<>();
            currencies.add(Currency.create("$", "USD", context.getString(R.string.usd)));
            currencies.add(Currency.create("€", "EUR", context.getString(R.string.eur)));
            currencies.add(Currency.create("₽", "RUB", context.getString(R.string.rub)));
            setValue(currencies);
         }
    }

}
