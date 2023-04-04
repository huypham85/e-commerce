package com.example.e_commerce.network.service;

import com.example.e_commerce.network.model.LoginRequest;
import com.example.e_commerce.network.model.LoginResponse;
import com.example.e_commerce.network.model.RegisterBody;
import com.example.e_commerce.network.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/signup")
    Call<RegisterResponse> register(@Body RegisterBody registerBody);

    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
