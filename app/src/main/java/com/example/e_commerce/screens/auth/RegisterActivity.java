package com.example.e_commerce.screens.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerce.databinding.ActivityRegisterBinding;
import com.example.e_commerce.network.model.request.RegisterBody;
import com.example.e_commerce.network.model.response.RegisterResponse;
import com.example.e_commerce.network.service.AuthService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class RegisterActivity extends AppCompatActivity {
    @Inject
    AuthService authService;
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.registerBtn.setOnClickListener(v -> {
            String name = binding.nameEditTxt.getText().toString().trim();
            String mail = binding.emailEditTxt.getText().toString().trim();
            String password = binding.passwordEdt.getText().toString().trim();
            Call<RegisterResponse> call = authService.register(new RegisterBody(name, mail, password, "USER"));
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this,"Network error or invalid email", Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    call.cancel();
                }
            });
        });
    }
}