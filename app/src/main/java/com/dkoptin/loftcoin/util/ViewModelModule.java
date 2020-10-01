package com.dkoptin.loftcoin.util;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory viewModelProvider(ViewModelFactory impl);

}
