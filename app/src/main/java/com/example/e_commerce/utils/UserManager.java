package com.example.e_commerce.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class UserManager {
    private final SharedPreferences sharedPreferences;
    @Inject
    public UserManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    static String ACCESS_TOKEN = "access_token";

    public void saveAccessToken(String accessToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCESS_TOKEN, accessToken).apply();
    }

    public String getAccessToken() {
        return sharedPreferences.getString(ACCESS_TOKEN,"");
    }
}
