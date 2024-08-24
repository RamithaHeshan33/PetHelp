package com.example.pethelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pethelp.databinding.ActivitySuccessPaymentBinding;

public class SuccessPayment extends AppCompatActivity {

    private ActivitySuccessPaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Initialize the binding object
        binding = ActivitySuccessPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set an OnClickListener on the backToHomeBtn
        binding.backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the added items list to refresh the memory
                Cart.addedItems.clear();

                // Redirect to the AddedItemsActivity with cleared stack
                Intent intent = new Intent(SuccessPayment.this, Cart.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });




    }

    public void backBtn(View v) {
        Intent intent = new Intent(SuccessPayment.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);       // Clear the activity stack
        startActivity(intent);
        finish();
    }

}
