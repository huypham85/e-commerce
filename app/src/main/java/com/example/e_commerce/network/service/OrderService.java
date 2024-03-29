package com.example.e_commerce.network.service;

import com.example.e_commerce.network.model.request.order.CreateOrderRequest;
import com.example.e_commerce.network.model.request.order.UpdateOrderRequest;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.order.Oder;
import com.example.e_commerce.network.model.response.order.OrderDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderService {
    @POST("oder/create")
    Call<ResponseAPI<String>> createOrder(@Body CreateOrderRequest createOrderRequest);

    @GET("oder/get-all-by-user")
    Call<ResponseAPI<List<Oder>>> getOrderByUser();

    @GET("oder/detail/{orderId}")
    Call<ResponseAPI<OrderDetail>> getOrderDetails(@Path("orderId") long id);

    @POST("oder/update")
    Call<ResponseAPI<String>> updateOrder(@Body UpdateOrderRequest updateOrderRequest);
}
