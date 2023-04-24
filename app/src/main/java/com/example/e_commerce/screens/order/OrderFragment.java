package com.example.e_commerce.screens.order;

import static androidx.navigation.Navigation.findNavController;
import static com.example.e_commerce.screens.cart.CartFragment.CompanionObject.CART_ITEMS;
import static com.example.e_commerce.screens.cart.CartFragment.CompanionObject.TOTAL_PRICE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentOrderBinding;
import com.example.e_commerce.network.model.request.order.CreateOrderRequest;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.cart.CartItem;
import com.example.e_commerce.network.model.response.profile.CurrentUserResponse;
import com.example.e_commerce.network.service.OrderService;
import com.example.e_commerce.network.service.ProfileService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

enum PaymentMethod {
    PAID(1),
    COD(0);
    private int numVal;

    PaymentMethod(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

    public void setNumVal(int numVal) {
        this.numVal = numVal;
    }
}

@AndroidEntryPoint
public class OrderFragment extends Fragment {
    FragmentOrderBinding binding;
    OrderItemAdapter orderItemAdapter;
    List<CartItem> cartItems;
    PaymentMethod paymentMethod = PaymentMethod.COD;
    CurrentUserResponse userInfo;
    @Inject
    ProfileService profileService;
    @Inject
    OrderService orderService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(inflater, container, false);
        if (getArguments() != null) {
            cartItems = getArguments().getParcelableArrayList(CART_ITEMS);
            binding.costValue.setText(String.valueOf(getArguments().getFloat(TOTAL_PRICE)));
            setUpOrderItems();
            setUpUserInfo();
        }
        binding.radioGroup.check(R.id.codCheckbox);
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = binding.getRoot().findViewById(checkedId);
                String checkedText = checkedRadioButton.getText().toString();
                if (checkedText.equals("Đã thanh toán")) {
                    paymentMethod = PaymentMethod.PAID;
                } else {
                    paymentMethod = PaymentMethod.COD;
                }
                Toast.makeText(requireContext(), "You selected: " + checkedText + checkedId, Toast.LENGTH_SHORT).show();
            }
        });
        binding.confirmOrderBtn.setOnClickListener(v -> {
            switch (paymentMethod) {
                case PAID:
                    createPaidOrder();
                    break;
                case COD:
                    createCODOrder();
                    break;
            }
        });
        return binding.getRoot();
    }

    private void createPaidOrder() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("USER_INFO", userInfo);
        bundle.putParcelableArrayList(CART_ITEMS, new ArrayList<>(cartItems));
        findNavController(getView()).navigate(R.id.action_orderFragment_to_confirmPaidFragment, bundle);
    }

    private void createCODOrder() {
        Call<ResponseAPI<String>> call = orderService.createOrder(new CreateOrderRequest(null, userInfo.getTelephoneNumber(), userInfo.getDeliveryAddress(), paymentMethod.getNumVal(), cartItems.stream().map(CartItem::getId).collect(Collectors.toList())));

        call.enqueue(new Callback<ResponseAPI<String>>() {
            @Override
            public void onResponse(Call<ResponseAPI<String>> call, Response<ResponseAPI<String>> response) {
                if (response.isSuccessful()) {
                    findNavController(getView()).navigate(R.id.action_orderFragment_to_profileFragment);
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI<String>> call, Throwable t) {

            }
        });
    }

    private void setUpUserInfo() {
        Call<ResponseAPI<CurrentUserResponse>> call = profileService.getUserInfo();

        call.enqueue(new Callback<ResponseAPI<CurrentUserResponse>>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseAPI<CurrentUserResponse>> call, Response<ResponseAPI<CurrentUserResponse>> response) {
                if (response.isSuccessful()) {
                    CurrentUserResponse currentUserResponse = response.body().getData();
                    userInfo = currentUserResponse;
                    binding.nameValue.setText(currentUserResponse.getName());
                    binding.phoneValue.setText(currentUserResponse.getTelephoneNumber());
                    binding.addressValue.setText(currentUserResponse.getDeliveryAddress());
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI<CurrentUserResponse>> call, Throwable t) {

            }
        });
    }

    private void setUpOrderItems() {
        orderItemAdapter = new OrderItemAdapter(cartItems, requireContext());
        binding.orderItemsRcv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.orderItemsRcv.setAdapter(orderItemAdapter);
    }
}