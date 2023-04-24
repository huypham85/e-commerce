package com.example.e_commerce.network.service;

import com.example.e_commerce.network.model.request.cart.AddToCartRequest;
import com.example.e_commerce.network.model.request.order.CreateOrderRequest;
import com.example.e_commerce.network.model.response.ResponseAPI;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderService {
    @POST("oder/create")
    Call<ResponseAPI<String>> createOrder(@Body CreateOrderRequest createOrderRequest);
}
