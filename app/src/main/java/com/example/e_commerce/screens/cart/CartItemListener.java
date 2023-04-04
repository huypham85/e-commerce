package com.example.e_commerce.screens.cart;

import com.example.e_commerce.model.ProductModel;

public interface CartItemListener {
    void increaseQuantity(int position);
    void decreaseQuantity(int position);
    void deleteItem(int position);
    void onSelectCartItem(float price, int position, boolean isChecked);
    void chooseCartItem(int position);
}
