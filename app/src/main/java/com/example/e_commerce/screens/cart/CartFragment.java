package com.example.e_commerce.screens.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e_commerce.databinding.FragmentCartBinding;
import com.example.e_commerce.model.CartItemModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements CartItemListener {
    List<CartItemModel> cartItemsList;
    CartProductAdapter cartProductAdapter;
    Float totalPrice;
    private FragmentCartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false);

        cartItemsList = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            CartItemModel productModel = new CartItemModel("Ao thun",
                    10000f,
                    "con hang",
                    "https://ih1.redbubble.net/image.4646407321.9310/ssrco,classic_tee,mens,fafafa:ca443f4786,front_alt,square_product,600x600.jpg",
                    "Áo giữ nhiệt cao cấp, chất liệu thun lụa lạnh co dãn 4 chiều",
                    i + 1,
                    false);
            cartItemsList.add(productModel);
        }

        totalPrice = 0f;
        binding.totalPriceTxt.setText(totalPrice.toString());
        cartProductAdapter = new CartProductAdapter(cartItemsList, requireContext());
        cartProductAdapter.setCartItemListener(this);
        binding.cartRcv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.cartRcv.setAdapter(cartProductAdapter);

        return binding.getRoot();
    }

    @Override
    public void increaseQuantity(int position) {
        CartItemModel item = cartItemsList.get(position);
        int currentQuantity = item.getQuantity();
        cartItemsList.set(position, new CartItemModel(
                        item.getProductName(),
                        item.getProductPrice(),
                        item.getProductStatus(),
                        item.getProductImageURL(),
                        item.getProductDesc(),
                        currentQuantity + 1,
                        item.isChecked()
                )
        );
        cartProductAdapter.setData(cartItemsList, position);
    }

    @Override
    public void decreaseQuantity(int position) {
        CartItemModel item = cartItemsList.get(position);
        if (item.getQuantity() > 1) {
            int currentQuantity = item.getQuantity();
            cartItemsList.set(position, new CartItemModel(
                            item.getProductName(),
                            item.getProductPrice(),
                            item.getProductStatus(),
                            item.getProductImageURL(),
                            item.getProductDesc(),
                            currentQuantity - 1,
                            item.isChecked()
                    )
            );
            cartProductAdapter.setData(cartItemsList, position);
        }
    }

    @Override
    public void deleteItem(int position) {
        cartItemsList.remove(position);
        cartProductAdapter.setDataAfterRemove(cartItemsList);
        cartProductAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onSelectCartItem(float price, int position, boolean isChecked) {

        CartItemModel item = cartItemsList.get(position);
        cartItemsList.set(position, new CartItemModel(
                        item.getProductName(),
                        item.getProductPrice(),
                        item.getProductStatus(),
                        item.getProductImageURL(),
                        item.getProductDesc(),
                        item.getQuantity(),
                        isChecked
                )
        );
        cartProductAdapter.setData(cartItemsList, position);
        totalPrice += price;
        binding.totalPriceTxt.setText(totalPrice.toString());
    }

    @Override
    public void chooseCartItem(int position) {

    }
}