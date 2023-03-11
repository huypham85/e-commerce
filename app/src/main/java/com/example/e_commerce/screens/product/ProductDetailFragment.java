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

public class ProductDetailFragment extends Fragment {

    private FragmentProductDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        ProductModel productModel = null;
        if (getArguments() != null) {
            System.out.println("bundle"+getArguments());
            productModel = getArguments().getParcelable(ITEM_KEY);
            Glide.with(requireContext())
                    .load(productModel.getProductImageURL())
                    .into(binding.productImg);
            binding.productNameTxt.setText(productModel.getProductName());
            binding.productDescTxt.setText(productModel.getProductDesc());
            binding.productPriceTxt.setText(productModel.getProductPrice().toString());
            binding.statusTxt.setText(productModel.getProductStatus());
        }

        return binding.getRoot();
    }
}