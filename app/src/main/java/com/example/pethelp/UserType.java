package com.example.pethelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pethelp.databinding.ActivityUserTypeBinding;

public class UserType extends AppCompatActivity {

    private ActivityUserTypeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserTypeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.petOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserType.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        binding.admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserType.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
