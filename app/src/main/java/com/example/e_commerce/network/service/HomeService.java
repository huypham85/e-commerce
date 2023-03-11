package com.example.e_commerce.network.service;

import com.example.e_commerce.model.ProductModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeService {
    @GET("mydata")
    Call<ProductModel> getMyData();
}
