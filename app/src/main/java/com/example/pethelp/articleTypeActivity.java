package com.example.pethelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pethelp.databinding.ActivityArticleTypeBinding;

public class articleTypeActivity extends AppCompatActivity {

    private ActivityArticleTypeBinding binding;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArticleTypeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.AddArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(articleTypeActivity.this, AddArticleActivity.class);
                startActivity(intent);
            }
        });

        binding.viewArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(articleTypeActivity.this, ViewArticleActivity.class);
                startActivity(intent);
            }
        });

        binding.footerWelcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(articleTypeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.footerFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(articleTypeActivity.this, Cart.class);
                startActivity(intent);
            }
        });

        binding.footerCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(articleTypeActivity.this, AddedItemsActivity.class);
                startActivity(intent);
            }
        });
    }
}
