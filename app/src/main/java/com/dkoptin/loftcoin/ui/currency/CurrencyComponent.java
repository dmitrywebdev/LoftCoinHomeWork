package com.dkoptin.loftcoin.ui.currency;

import androidx.lifecycle.ViewModelProvider;

import com.dkoptin.loftcoin.BaseComponent;
import com.dkoptin.loftcoin.util.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        CurrencyModule.class,
        ViewModelModule.class
}, dependencies = {
        BaseComponent.class
})
abstract class CurrencyComponent {

    abstract ViewModelProvider.Factory viewModelFactory();

}
