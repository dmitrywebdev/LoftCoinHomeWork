package com.dkoptin.loftcoin;

import android.content.Context;

import com.dkoptin.loftcoin.data.CoinsRepo;
import com.dkoptin.loftcoin.data.CurrencyRepo;
import com.dkoptin.loftcoin.util.ImageLoader;

public interface BaseComponent {
    Context context();
    CoinsRepo coinsRepo();
    CurrencyRepo currencyRepo();
    ImageLoader imageLoader();
}
