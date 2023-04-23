package com.example.e_commerce.screens.cart;

import static androidx.navigation.Navigation.findNavController;
import static com.example.e_commerce.screens.cart.CartFragment.CompanionObject.CART_ITEMS;
import static com.example.e_commerce.screens.cart.CartFragment.CompanionObject.TOTAL_PRICE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentCartBinding;
import com.example.e_commerce.network.model.request.cart.AddToCartRequest;
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
    ArrayList<CartItem> selectedItems;
    CartProductAdapter cartProductAdapter;
    Float totalPrice;
    @Inject
    CartService cartService;
    private FragmentCartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false);
        setUpCartItems();

        totalPrice = 0f;
        selectedItems = new ArrayList<>();
        binding.totalPriceTxt.setText(totalPrice.toString());
        binding.orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(CART_ITEMS, selectedItems);
                bundle.putFloat(TOTAL_PRICE, totalPrice);
                if (!selectedItems.isEmpty()) {
                    System.out.println("list items:"+selectedItems.size());
                    findNavController(getView()).navigate(R.id.action_cartFragment_to_orderFragment, bundle);
                }
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        selectedItems.clear();
    }

    private void setUpCartItems() {
        Call<ResponseAPI<List<CartItem>>> call = cartService.getCartItems();
        call.enqueue(new Callback<ResponseAPI<List<CartItem>>>() {
            @Override
            public void onResponse(Call<ResponseAPI<List<CartItem>>> call, Response<ResponseAPI<List<CartItem>>> response) {
                if (response.isSuccessful()) {
                    cartItemsList = response.body().getData();
                    cartProductAdapter = new CartProductAdapter(cartItemsList, requireContext());
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
        CartItem item = cartItemsList.get(position);
        long currentQuantity = item.getQuantity();
        Call<ResponseAPI<String>> call = cartService.updateCartItem(
                new AddToCartRequest(
                        item.getId(),
                        item.getProductId(),
                        currentQuantity + 1
                ));
        call.enqueue(new Callback<ResponseAPI<String>>() {
            @Override
            public void onResponse(Call<ResponseAPI<String>> call, Response<ResponseAPI<String>> response) {
                if (response.isSuccessful()) {
                    cartItemsList.set(position, new CartItem(
                                    currentQuantity + 1,
                                    item.getProductCost(),
                                    item.getProductId(),
                                    item.getId(),
                                    item.getProductName(),
                                    item.getProductDescription(),
                                    item.getStatus(),
                                    item.getProductFilePath()
                            )
                    );
                    cartProductAdapter.setData(cartItemsList, position);
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI<String>> call, Throwable t) {

            }
        });

    }

    @Override
    public void decreaseQuantity(int position) {
        CartItem item = cartItemsList.get(position);
        long currentQuantity = item.getQuantity();
        if (currentQuantity > 1) {
            Call<ResponseAPI<String>> call = cartService.updateCartItem(
                    new AddToCartRequest(
                            item.getId(),
                            item.getProductId(),
                            currentQuantity - 1
                    ));
            call.enqueue(new Callback<ResponseAPI<String>>() {
                @Override
                public void onResponse(Call<ResponseAPI<String>> call, Response<ResponseAPI<String>> response) {
                    if (response.isSuccessful()) {
                        cartItemsList.set(position, new CartItem(
                                        currentQuantity - 1,
                                        item.getProductCost(),
                                        item.getProductId(),
                                        item.getId(),
                                        item.getProductName(),
                                        item.getProductDescription(),
                                        item.getStatus(),
                                        item.getProductFilePath()
                                )
                        );
                        cartProductAdapter.setData(cartItemsList, position);
                    }
                }

                @Override
                public void onFailure(Call<ResponseAPI<String>> call, Throwable t) {

                }
            });
        }

    }

    @Override
    public void deleteItem(int position) {
        CartItem item = cartItemsList.get(position);
        Call<ResponseAPI<String>> call = cartService.deleteCartItem(item.getId());
        call.enqueue(new Callback<ResponseAPI<String>>() {
            @Override
            public void onResponse(Call<ResponseAPI<String>> call, Response<ResponseAPI<String>> response) {
                if (response.isSuccessful()) {
                    cartItemsList.remove(position);
                    cartProductAdapter.setDataAfterRemove(cartItemsList, position);
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI<String>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onSelectCartItem(float price, int position, boolean isChecked) {

        CartItem item = cartItemsList.get(position);
        if (isChecked) {
            selectedItems.add(item);
        } else {
            selectedItems.remove(cartItemsList.get(position));
        }
        totalPrice += price;
        binding.totalPriceTxt.setText(totalPrice.toString());
    }

    @Override
    public void chooseCartItem(int position) {

    }

    public static class CompanionObject {
        public static final String CART_ITEMS = "CartItem";
        public static final String TOTAL_PRICE = "TotalPrice";
    }
}