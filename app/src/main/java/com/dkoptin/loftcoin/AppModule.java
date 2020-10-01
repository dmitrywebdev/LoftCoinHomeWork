package com.dkoptin.loftcoin;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Provides
    static Context context(Application app) {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    static ExecutorService ioExecutor() {
        int poolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        return Executors.newFixedThreadPool(poolSize);
    }
}
