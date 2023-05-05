package com.example.e_commerce.screens.profile;

import com.example.e_commerce.network.model.response.order.UserOrderResponse;

public interface OnClickUserOrder {
    void clickOrder(UserOrderResponse order);
}
