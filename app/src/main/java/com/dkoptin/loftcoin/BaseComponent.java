package com.dkoptin.loftcoin;

import android.content.Context;

import com.dkoptin.loftcoin.data.CoinsRepo;
import com.dkoptin.loftcoin.data.CurrencyRepo;

public interface BaseComponent {
    Context context();
    CoinsRepo coinsRepo();
    CurrencyRepo currencyRepo();
}
