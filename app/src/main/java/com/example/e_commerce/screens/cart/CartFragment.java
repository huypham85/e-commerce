package com.example.e_commerce.screens.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e_commerce.databinding.FragmentCartBinding;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.cart.CartItem;
import com.example.e_commerce.network.service.CartService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class CartFragment extends Fragment implements CartItemListener {
    List<CartItem> cartItemsList;
    CartProductAdapter cartProductAdapter;
    Float totalPrice;
    private FragmentCartBinding binding;
    @Inject
    CartService cartService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false);
        setUpCartItems();

        totalPrice = 0f;
        binding.totalPriceTxt.setText(totalPrice.toString());


        return binding.getRoot();
    }

    private void setUpCartItems() {
        Call<ResponseAPI<List<CartItem>>> call = cartService.getCartItems();
        call.enqueue(new Callback<ResponseAPI<List<CartItem>>>() {
            @Override
            public void onResponse(Call<ResponseAPI<List<CartItem>>> call, Response<ResponseAPI<List<CartItem>>> response) {
                if (response.isSuccessful()) {
                    List<CartItem> cartItems = response.body().getData();
                    cartProductAdapter = new CartProductAdapter(cartItems, requireContext());
                    cartProductAdapter.setCartItemListener(CartFragment.this);
                    binding.cartRcv.setLayoutManager(new LinearLayoutManager(requireContext()));
                    binding.cartRcv.setAdapter(cartProductAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI<List<CartItem>>> call, Throwable t) {

            }
        });
    }

    @Override
    public void increaseQuantity(int position) {
//        CartItem item = cartItemsList.get(position);
//        int currentQuantity = item.getQuantity();
//        cartItemsList.set(position, new CartItem(
//                        item.getProductName(),
//                        item.getProductPrice(),
//                        item.getProductStatus(),
//                        item.getProductImageURL(),
//                        item.getProductDesc(),
//                        currentQuantity + 1,
//                        item.isChecked()
//                )
//        );
//        cartProductAdapter.setData(cartItemsList, position);
    }

    @Override
    public void decreaseQuantity(int position) {
//        CartItem item = cartItemsList.get(position);
//        if (item.getQuantity() > 1) {
//            int currentQuantity = item.getQuantity();
//            cartItemsList.set(position, new CartItem(
//                            item.getProductName(),
//                            item.getProductPrice(),
//                            item.getProductStatus(),
//                            item.getProductImageURL(),
//                            item.getProductDesc(),
//                            currentQuantity - 1,
//                            item.isChecked()
//                    )
//            );
//            cartProductAdapter.setData(cartItemsList, position);
//        }
    }

    @Override
    public void deleteItem(int position) {
        cartItemsList.remove(position);
        cartProductAdapter.setDataAfterRemove(cartItemsList);
        cartProductAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onSelectCartItem(float price, int position, boolean isChecked) {

//        CartItem item = cartItemsList.get(position);
//        cartItemsList.set(position, new CartItem(
//                        item.getProductName(),
//                        item.getProductPrice(),
//                        item.getProductStatus(),
//                        item.getProductImageURL(),
//                        item.getProductDesc(),
//                        item.getQuantity(),
//                        isChecked
//                )
//        );
//        cartProductAdapter.setData(cartItemsList, position);
//        totalPrice += price;
//        binding.totalPriceTxt.setText(totalPrice.toString());
    }

    @Override
    public void chooseCartItem(int position) {

    }
}