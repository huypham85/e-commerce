package com.example.e_commerce.network.service;

import com.example.e_commerce.network.model.request.cart.AddToCartRequest;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.cart.CartItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CartService {
    @POST("oder-item/create")
    Call<ResponseAPI<String>> addItemToCart(@Body AddToCartRequest addToCartRequest);

    @POST("oder-item/update")
    Call<ResponseAPI<String>> updateCartItem(@Body AddToCartRequest addToCartRequest);

    @GET("oder-item/get-all")
    Call<ResponseAPI<List<CartItem>>> getCartItems();
}
