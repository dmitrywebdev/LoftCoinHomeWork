package com.dkoptin.loftcoin.ui.main;

import com.dkoptin.loftcoin.BaseComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        MainModule.class
}, dependencies = {
        BaseComponent.class
})
public abstract class MainComponent {

    abstract void inject(MainActivity activity);

}
