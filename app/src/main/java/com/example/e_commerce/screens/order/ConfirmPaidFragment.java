package com.example.e_commerce.screens.order;

import static androidx.navigation.Navigation.findNavController;
import static com.example.e_commerce.screens.cart.CartFragment.CompanionObject.CART_ITEMS;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentConfirmPaidBinding;
import com.example.e_commerce.network.model.request.order.CreateOrderRequest;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.cart.CartItem;
import com.example.e_commerce.network.model.response.profile.CurrentUserResponse;
import com.example.e_commerce.network.service.OrderService;
import com.example.e_commerce.network.service.ProfileService;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class ConfirmPaidFragment extends Fragment {
    FragmentConfirmPaidBinding binding;
    List<CartItem> cartItems;
    CurrentUserResponse userInfo;
    @Inject
    OrderService orderService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentConfirmPaidBinding.inflate(inflater,container,false);
        if (getArguments()!= null) {
            cartItems = getArguments().getParcelableArrayList(CART_ITEMS);
            userInfo = getArguments().getParcelable("USER_INFO");
            binding.nameInfo.setText(userInfo.getName());
            binding.phoneNumberInfo.setText(userInfo.getTelephoneNumber());
            binding.addressInfo.setText(userInfo.getDeliveryAddress());
            binding.emailInfo.setText(userInfo.getEmail());
        }
        binding.confirmPaidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseAPI<String>> call = orderService.createOrder(new CreateOrderRequest(null, userInfo.getTelephoneNumber(), userInfo.getDeliveryAddress(), 1, cartItems.stream().map(CartItem::getId).collect(Collectors.toList())));

                call.enqueue(new Callback<ResponseAPI<String>>() {
                    @Override
                    public void onResponse(Call<ResponseAPI<String>> call, Response<ResponseAPI<String>> response) {
                        if (response.isSuccessful()) {
                            findNavController(getView()).navigate(R.id.action_confirmPaidFragment_to_profileFragment);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAPI<String>> call, Throwable t) {

                    }
                });
            }
        });
        return binding.getRoot();
    }
}