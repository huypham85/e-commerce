package com.example.e_commerce.screens.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.e_commerce.MainActivity;
import com.example.e_commerce.utils.UserManager;
import com.example.e_commerce.databinding.ActivityLoginBinding;
import com.example.e_commerce.network.model.request.LoginRequest;
import com.example.e_commerce.network.model.response.LoginResponse;
import com.example.e_commerce.network.service.AuthService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    @Inject
    AuthService authService;
    @Inject
    UserManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.signInButton.setOnClickListener(v -> {
            String email = binding.usernameInput.getText().toString();
            String password = binding.passwordInput.getText().toString();
            authService.login(new LoginRequest(email,password))
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                userManager.saveAccessToken(response.body().getAccessToken());
                                System.out.println("Access token " + userManager.getAccessToken());
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this,"Network error or invalid information", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                        }
                    });

        });

        binding.signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}