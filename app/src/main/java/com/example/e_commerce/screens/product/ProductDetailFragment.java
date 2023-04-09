package com.example.e_commerce.screens.product;

import static com.example.e_commerce.screens.home.HomeProductAdapter.CompanionObject.ITEM_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.e_commerce.databinding.FragmentProductDetailBinding;
import com.example.e_commerce.model.ProductModel;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.product.ProductResponse;
import com.example.e_commerce.network.service.HomeService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class ProductDetailFragment extends Fragment {
    @Inject
    HomeService homeService;
    private FragmentProductDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        ProductModel productModel = null;
        if (getArguments() != null) {
            System.out.println("bundle" + getArguments());
            productModel = getArguments().getParcelable(ITEM_KEY);
            Call<ResponseAPI<ProductResponse>> call = homeService.getProductDetail(productModel.getId());
            call.enqueue(new Callback<ResponseAPI<ProductResponse>>() {
                @Override
                public void onResponse(Call<ResponseAPI<ProductResponse>> call, Response<ResponseAPI<ProductResponse>> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getData() != null) {
                            ProductModel productDetail = new ProductModel(response.body().getData());
                            Glide.with(requireContext())
                                    .load(productDetail.getProductImageURL())
                                    .into(binding.productImg);
                            binding.productNameTxt.setText(productDetail.getProductName());
                            binding.productDescTxt.setText(productDetail.getProductDesc());
                            binding.productPriceTxt.setText(String.valueOf(productDetail.getProductPrice()));
                            binding.statusTxt.setText(productDetail.getProductStatus());
                        }

                    }
                }

                @Override
                public void onFailure(Call<ResponseAPI<ProductResponse>> call, Throwable t) {

                }
            });

        }

        return binding.getRoot();
    }
}