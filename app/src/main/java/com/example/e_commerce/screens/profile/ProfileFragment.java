package com.example.e_commerce.screens.profile;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentProfileBinding;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.order.Oder;
import com.example.e_commerce.network.model.response.profile.CurrentUserResponse;
import com.example.e_commerce.network.service.OrderService;
import com.example.e_commerce.network.service.ProfileService;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class ProfileFragment extends Fragment implements OnClickUserOrder{
    @Inject
    ProfileService profileService;
    @Inject
    OrderService orderService;
    List<Oder> userOrderList;
    private FragmentProfileBinding binding;
    UserOrderAdapter userOrderAdapter;
    CurrentUserResponse user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        setupOrderList();

        binding.editUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseAPI<CurrentUserResponse>> call = profileService.getUserInfo();

                call.enqueue(new Callback<ResponseAPI<CurrentUserResponse>>() {
                    @Override
                    public void onResponse(Call<ResponseAPI<CurrentUserResponse>> call, Response<ResponseAPI<CurrentUserResponse>> response) {
                        if (response.isSuccessful()) {
                            CurrentUserResponse user = response.body().getData();
                            Bundle bundle = new Bundle();
                            bundle.putString("name", user.getName());
                            bundle.putString("email", user.getEmail());
                            bundle.putString("telephoneNumber", user.getTelephoneNumber());
                            bundle.putString("deliveryAddress", user.getDeliveryAddress());
                            findNavController(getView()).navigate(R.id.action_profileFragment_to_profileEditFragment, bundle);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAPI<CurrentUserResponse>> call, Throwable t) {

                    }
                });
            }
        });

        Call<ResponseAPI<CurrentUserResponse>> call = profileService.getUserInfo();
        call.enqueue(new Callback<ResponseAPI<CurrentUserResponse>>() {
            @Override
            public void onResponse(Call<ResponseAPI<CurrentUserResponse>> call, Response<ResponseAPI<CurrentUserResponse>> response) {
                if (response.isSuccessful()) {
                    user = response.body().getData();
                    binding.nameInfo.setText(user.getName());
                    binding.emailInfo.setText(user.getEmail());
                    binding.phoneNumberInfo.setText(user.getTelephoneNumber());
                    binding.addressInfo.setText(user.getDeliveryAddress());
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI<CurrentUserResponse>> call, Throwable t) {

            }
        });

        return binding.getRoot();
    }

    private void setupOrderList() {
        Call<ResponseAPI<List<Oder>>> call = orderService.getOrderByUser();
        call.enqueue(new Callback<ResponseAPI<List<Oder>>>() {
            @Override
            public void onResponse(Call<ResponseAPI<List<Oder>>> call, Response<ResponseAPI<List<Oder>>> response) {
                userOrderList = response.body().getData();
                userOrderAdapter = new UserOrderAdapter(userOrderList, requireContext());
                binding.orderRcv.setLayoutManager(new LinearLayoutManager(requireContext()));
                userOrderAdapter.onClickUserOrder = ProfileFragment.this;
                binding.orderRcv.setAdapter(userOrderAdapter);
            }

            @Override
            public void onFailure(Call<ResponseAPI<List<Oder>>> call, Throwable t) {

            }
        });
    }

    public void clickOrder(Oder order) {
        Bundle bundle = new Bundle();
        bundle.putLong("id", order.getid());
        findNavController(getView()).navigate(R.id.action_profileFragment_to_orderFragment, bundle);
    }

}