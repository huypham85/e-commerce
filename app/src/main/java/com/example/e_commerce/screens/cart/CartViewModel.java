package com.example.e_commerce.screens.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commerce.model.CartItemModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

public class CartViewModel extends ViewModel {

    private MutableLiveData<List<CartItemModel>> _cartItems = new MutableLiveData<>();

    public LiveData<List<CartItemModel>> cartItems() {
        return _cartItems;
    }

    public CartViewModel() {
        List<CartItemModel> cartItemsList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            CartItemModel productModel = new CartItemModel("Ao thun",
                    10000f,
                    "con hang",
                    "https://ih1.redbubble.net/image.4646407321.9310/ssrco,classic_tee,mens,fafafa:ca443f4786,front_alt,square_product,600x600.jpg",
                    "Áo giữ nhiệt cao cấp, chất liệu thun lụa lạnh co dãn 4 chiều",
                    2,
                    false);
            cartItemsList.add(productModel);
        }
        _cartItems.postValue(cartItemsList);
    }
}
