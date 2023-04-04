package com.example.e_commerce.di;

import com.example.e_commerce.Common;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(
            OkHttpClient client
    ) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setLenient();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gsonBuilder.create());
        return new Retrofit.Builder()
                .baseUrl(Common.baseURL)
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build();
    }
}