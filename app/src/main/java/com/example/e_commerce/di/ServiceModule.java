package com.example.e_commerce.di;

import com.example.e_commerce.network.service.AuthService;
import com.example.e_commerce.network.service.ProductService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public class ServiceModule {
    @Singleton
    @Provides
    public ProductService provideHomeService(Retrofit retrofit) {
        return retrofit.create(ProductService.class);
    }

    @Singleton
    @Provides
    public AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }
}
