package com.example.pethelp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pethelp.databinding.ActivityAddFoodBinding;

public class AddFood extends AppCompatActivity {

    private ActivityAddFoodBinding binding;
    private AddFoodDBOpenHelper addFoodDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize database helper
        addFoodDBOpenHelper = new AddFoodDBOpenHelper(this);

        // Set button click listeners
        binding.btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFood.this, SeeProducts.class);
                startActivity(intent);
            }
        });

        binding.btnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(v);
            }
        });
    }

    public void addData(View v) {
        SQLiteDatabase db = addFoodDBOpenHelper.getWritableDatabase();

        EditText foodid = findViewById(R.id.foodid);
        EditText brandname = findViewById(R.id.brandname);
        EditText price = findViewById(R.id.price);
        EditText note = findViewById(R.id.note);

        String foodidValue = foodid.getText().toString();
        String brandnameText = brandname.getText().toString();
        String priceText = price.getText().toString();
        String noteText = note.getText().toString();

        if (foodidValue.isEmpty() || brandnameText.isEmpty() || priceText.isEmpty() || noteText.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show();
            return;
        }

        addFoodDBOpenHelper.insertFood(db, foodidValue, brandnameText, priceText, noteText);

        Toast.makeText(this, "Record Added Successfully", Toast.LENGTH_LONG).show();
        foodid.setText("");
        brandname.setText("");
        price.setText("");
        note.setText("");
        db.close();
    }

}
