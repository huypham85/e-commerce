package com.example.e_commerce.model;

public class CartItemModel extends ProductModel {
    int quantity;

    boolean isChecked;

    public CartItemModel(String productName, long productPrice, String productStatus, String productImageURL, String productDesc, int quantity, boolean isChecked) {
        super(productName, productPrice, productStatus, productImageURL, productDesc);
        this.quantity = quantity;
        this.isChecked = isChecked;
    }

    public CartItemModel(String productName, long productPrice, String productStatus, String productImageURL, String productDesc) {
        super(productName, productPrice, productStatus, productImageURL, productDesc);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
