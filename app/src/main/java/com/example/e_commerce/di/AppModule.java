package com.example.e_commerce.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.e_commerce.R;
import com.example.e_commerce.utils.UserManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences(
                context.getString(R.string.app_name),
                Context.MODE_PRIVATE
        );
    }

    @Singleton
    @Provides
    UserManager provideUserManager(
            SharedPreferences sharedPreferences
    ) {
        return new UserManager(sharedPreferences);
    }
}
