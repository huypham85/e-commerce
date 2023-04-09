package com.example.e_commerce.di;

import com.example.e_commerce.utils.Common;
import com.example.e_commerce.utils.UserManager;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(
            UserManager userManager
    ) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder1 = originalRequest.newBuilder().addHeader("Authorization", "Bearer " + userManager.getAccessToken());
            Request newRequest = builder1.build();
            return chain.proceed(newRequest);
        });

        builder.addInterceptor(loggingInterceptor);

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