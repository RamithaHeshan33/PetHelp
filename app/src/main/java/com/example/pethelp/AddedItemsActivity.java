package com.example.pethelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddedItemsActivity extends AppCompatActivity implements MyAdapter.OnTotalValueChangeListener {

    ImageView footerWelcomeBtn;
    ImageView footerFoodBtn;
    ImageView articlesBtn;

    private TextView totalValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_added_items);

        // Initialize the ImageView variables after setContentView()
        footerWelcomeBtn = findViewById(R.id.footerWelcomeBtn);
        footerFoodBtn = findViewById(R.id.footerFoodBtn);
        articlesBtn = findViewById(R.id.articles);

        // Initialize the total value TextView
        totalValueTextView = findViewById(R.id.totalValue);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Pass true to hide the "Add" button, true to show quantity and '-' button, and true to show delete button
        MyAdapter adapter = new MyAdapter(this, Cart.addedItems, true, true, true, this);
        recyclerView.setAdapter(adapter);

        Button payButton = findViewById(R.id.payButton);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to PaymentSuccessActivity
                Intent intent = new Intent(AddedItemsActivity.this, SuccessPayment.class);
                startActivity(intent);
            }
        });

        // Set click listeners for footer buttons
        footerWelcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddedItemsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        footerFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddedItemsActivity.this, Cart.class);
                startActivity(intent);
            }
        });

        articlesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddedItemsActivity.this, articleTypeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onTotalValueChanged(double total) {
        // Update the total value displayed in the TextView
        totalValueTextView.setText("Total: Rs. " + String.format("%.2f", total));
    }
}
