package com.example.pethelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pethelp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private String loggedInUserEmail;
    private String loggedInUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve the user email and password passed from LoginActivity
        Intent intent = getIntent();
        loggedInUserEmail = intent.getStringExtra("USER_EMAIL");
        loggedInUserPassword = intent.getStringExtra("USER_PASSWORD");

        // Set up the profile button click listener
        Button profileButton = findViewById(R.id.profile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                intent.putExtra("USER_EMAIL", loggedInUserEmail); // Pass the actual user email
                intent.putExtra("USER_PASSWORD", loggedInUserPassword); // Pass the actual user password
                startActivity(intent);
            }
        });

        // Set up the welcome add pet button click listener
        binding.welcomeAddPetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, pet_register.class);
                startActivity(intent);
            }
        });

        // Set up the welcome pet food button click listener
        binding.welcomePetfoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Productlist.class);
                startActivity(intent);
            }
        });

        // Set up the footer welcome button click listener
        binding.footerFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cart.class);
                startActivity(intent);
            }
        });

        binding.footerCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddedItemsActivity.class);
                startActivity(intent);
            }
        });

        binding.articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, articleTypeActivity.class);
                startActivity(intent);
            }
        });
    }
}
