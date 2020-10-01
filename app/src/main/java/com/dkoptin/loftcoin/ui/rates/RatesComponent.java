package com.dkoptin.loftcoin.ui.rates;

import androidx.lifecycle.ViewModelProvider;

import com.dkoptin.loftcoin.BaseComponent;
import com.dkoptin.loftcoin.util.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        RatesModule.class,
        ViewModelModule.class
}, dependencies = {
        BaseComponent.class
})
abstract class RatesComponent {

    abstract ViewModelProvider.Factory viewModelFactory();


}
