package com.dkoptin.loftcoin;

import android.app.Application;
import android.content.Context;

import com.dkoptin.loftcoin.data.CmcApi;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

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

    @Provides
    @Singleton
    static OkHttpClient httpClient(ExecutorService executor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.dispatcher(new Dispatcher(executor));
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            interceptor.redactHeader(CmcApi.API_KEY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    @Provides
    @Singleton
    static Picasso picasso(Context context, OkHttpClient httpClient, ExecutorService executor) {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(httpClient))
                .executor(executor)
                .build();
    }

}
