package com.example.pethelp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Productlist extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> productid, productname, brandname, price;
    AddFoodDBOpenHelper DB;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_productlist);

        DB = new AddFoodDBOpenHelper(this);
        productid = new ArrayList<>();
        productname = new ArrayList<>();
        brandname = new ArrayList<>();
        price = new ArrayList<>();


        recyclerView = findViewById(R.id.recyclerview);
        adapter = new Adapter(this,productid,productname,brandname,price);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = DB.getdata();
        if (cursor.getCount()==0) {
            Toast.makeText(Productlist.this, "No Records", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while (cursor.moveToNext()) {
                productid.add(cursor.getString(0));
                productname.add(cursor.getString(1));
                brandname.add(cursor.getString(2));
                price.add(cursor.getString(3));

            }
        }
    }


}