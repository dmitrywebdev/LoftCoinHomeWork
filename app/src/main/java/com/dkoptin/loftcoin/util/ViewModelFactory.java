package com.dkoptin.loftcoin.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Provides;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Map<Class<?>, Provider<ViewModel>> provides;

    @Inject
    ViewModelFactory(Map<Class<?>, Provider<ViewModel>> provides) {
        this.provides = provides;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        final Provider<ViewModel> provider = provides.get(modelClass);
        if (provider != null) {
            return (T) provider.get();
        }
        return super.create(modelClass);
    }
}
