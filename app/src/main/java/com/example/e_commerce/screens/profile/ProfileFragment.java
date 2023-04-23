package com.example.e_commerce.screens.profile;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
                findNavController(getView()).navigate(R.id.action_profileFragment_to_profileEditFragment);
            }
        });

        Call<ResponseAPI<CurrentUserResponse>> call = profileService.getUserInfo();

        call.enqueue(new Callback<ResponseAPI<CurrentUserResponse>>() {
            @Override
            public void onResponse(Call<ResponseAPI<CurrentUserResponse>> call, Response<ResponseAPI<CurrentUserResponse>> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI<CurrentUserResponse>> call, Throwable t) {

            }
        });

        return binding.getRoot();
    }

}