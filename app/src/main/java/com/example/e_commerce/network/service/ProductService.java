package com.example.e_commerce.network.service;

import com.example.e_commerce.network.model.request.SearchProductRequest;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.product.GetProductResponse;
import com.example.e_commerce.network.model.response.product.ProductResponse;
import com.example.e_commerce.network.model.response.product.TypeProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProductService {
    @POST("product/search")
    Call<ResponseAPI<GetProductResponse>> searchProducts(@Query("page") int page, @Query("size") int size, @Body SearchProductRequest searchProductRequest);

    @GET("product/detail")
    Call<ResponseAPI<ProductResponse>> getProductDetail(@Query("id") long id);

    @POST("type-product/search")
    Call<ResponseAPI<List<TypeProductResponse>>> getProductTypes();
}
