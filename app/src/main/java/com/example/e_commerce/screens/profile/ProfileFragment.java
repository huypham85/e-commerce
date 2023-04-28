package com.example.e_commerce.screens.profile;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentProfileBinding;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.profile.CurrentUserResponse;
import com.example.e_commerce.network.service.ProfileService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {
    @Inject
    ProfileService profileService;
    private FragmentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        binding.editUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make an API call to get the User data
                Call<ResponseAPI<CurrentUserResponse>> call = profileService.getUserInfo();

                call.enqueue(new Callback<ResponseAPI<CurrentUserResponse>>() {
                    @Override
                    public void onResponse(Call<ResponseAPI<CurrentUserResponse>> call, Response<ResponseAPI<CurrentUserResponse>> response) {
                        if (response.isSuccessful()) {
                            CurrentUserResponse user = response.body().getData();
                            // Add the JSON string to the Bundle
                            Bundle bundle = new Bundle();
                            bundle.putString("name", user.getName());
                            bundle.putString("email", user.getEmail());
                            bundle.putString("telephoneNumber", user.getTelephoneNumber());
                            bundle.putString("deliveryAddress", user.getDeliveryAddress());
                            // Navigate to the profileEditFragment with the Bundle containing the JSON string
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
                    CurrentUserResponse user = response.body().getData();
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

}