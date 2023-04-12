package com.example.e_commerce.screens.home.spinner;

import com.example.e_commerce.network.model.response.product.TypeProductResponse;

public interface OnSpinnerItemSelected {
    void onCategoryItemSelected(TypeProductResponse typeProductResponse);
}
