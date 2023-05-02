package com.example.e_commerce.network.service;

import com.example.e_commerce.network.model.request.order.CreateOrderRequest;
import com.example.e_commerce.network.model.response.UserOrderResponse;
import com.example.e_commerce.network.model.response.ResponseAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrderService {
    @POST("oder/create")
    Call<ResponseAPI<String>> createOrder(@Body CreateOrderRequest createOrderRequest);

    @GET("oder/get-all-by-user")
    Call<ResponseAPI<List<UserOrderResponse>>> getOrderByUser();

    //@GET("oder/details")
    //Call<ResponseAPI> getOrderdetails();
}
