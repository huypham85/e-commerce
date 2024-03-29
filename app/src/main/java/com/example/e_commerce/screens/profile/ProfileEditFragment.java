package com.example.e_commerce.screens.profile;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentProfileBinding;
import com.example.e_commerce.databinding.FragmentProfileEditBinding;
import com.example.e_commerce.network.model.request.UpdateUserRequest;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.profile.UpdateUserResponse;
import com.example.e_commerce.network.service.ProfileService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class ProfileEditFragment extends Fragment {

    @Inject
    ProfileService profileService;
    private FragmentProfileEditBinding binding;
    private UpdateUserRequest updateUserRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileEditBinding.inflate(inflater, container, false);
        // initiate updateUserRequest
        if (getArguments() != null) {
            String name = getArguments().getString("name");
            String email = getArguments().getString("email");
            String phone = getArguments().getString("telephoneNumber");
            String address = getArguments().getString("deliveryAddress");

            binding.editName.setText(name);
            binding.editEmail.setText(email);
            binding.editPhone.setText(phone);
            binding.editAddress.setText(address);
        }
        binding.updateUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.editPhone.getText().toString();
                String address = binding.editAddress.getText().toString();
                updateUserRequest = new UpdateUserRequest(phone, address);
                Call<ResponseAPI<UpdateUserResponse>> call = profileService.updateUserInfo(updateUserRequest);

                call.enqueue(new Callback<ResponseAPI<UpdateUserResponse>>() {
                    @Override
                    public void onResponse(Call<ResponseAPI<UpdateUserResponse>> call, Response<ResponseAPI<UpdateUserResponse>> response) {
                        if (response.isSuccessful()) {
                            findNavController(getView()).navigate(R.id.action_profileEditFragment_to_profileFragment);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAPI<UpdateUserResponse>> call, Throwable t) {

                    }
                });
            }
        });


        return binding.getRoot();
    }
}