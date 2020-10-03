package com.dkoptin.loftcoin.data;

import android.content.Context;

import androidx.room.Room;

import com.dkoptin.loftcoin.BuildConfig;
import com.squareup.moshi.Moshi;

import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public abstract class DataModule {

    @Provides
    static Moshi moshi() {
        Moshi moshi = new Moshi.Builder().build();
        return moshi.newBuilder()
                .add(Coin.class, moshi.adapter(AutoValue_CmcCoin.class))
                .add(Listings.class, moshi.adapter(AutoValue_Listings.class))
                .build();
    }

    @Provides
    static Retrofit retrofit(OkHttpClient httpClient, Moshi moshi) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(httpClient.newBuilder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    return chain.proceed(request.newBuilder()
                        .addHeader(CmcApi.API_KEY, BuildConfig.API_KEY)
                        .build());
                })
                .build());
        builder.baseUrl(BuildConfig.API_ENDPOINT);
        builder.addConverterFactory(MoshiConverterFactory.create(moshi));
        return builder.build();
    }

    @Provides
    static CmcApi cmcApi(Retrofit retrofit) {
        return retrofit.create(CmcApi.class);
    }

    @Provides
    @Singleton
    static LoftDatabase loftDatabase(Context context) {
        if (BuildConfig.DEBUG) {
            return Room.inMemoryDatabaseBuilder(context, LoftDatabase.class).build();
        } else {
            return Room.databaseBuilder(context, LoftDatabase.class, "loft.db").build();
        }
    }

    @Binds
    abstract CoinsRepo coinsRepo(CmcCoinsRepo impl);

    @Binds
    abstract CurrencyRepo currencyRepo(CurrencyRepoImpl impl);
}
