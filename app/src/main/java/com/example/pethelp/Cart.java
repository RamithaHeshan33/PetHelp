package com.example.pethelp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    List<item> items = new ArrayList<>();
    static List<item> addedItems = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        EditText searchBar = findViewById(R.id.search_bar);
        RadioGroup sortRadioGroup = findViewById(R.id.sortRadioGroup);
        RadioButton highPriceRadioButton = findViewById(R.id.highPriceRadioButton);
        RadioButton lowPriceRadioButton = findViewById(R.id.lowPriceRadioButton);
        ImageView footerWelcomeBtn = findViewById(R.id.footerWelcomeBtn);
        ImageView footerFoodBtn = findViewById(R.id.footerFoodBtn);
        ImageView footerCartBtn = findViewById(R.id.footerCartBtn);
        ImageView articlesBtn = findViewById(R.id.articles);

        // Adding items to the list
        items.add(new item("Royal Dog", "100", R.drawable.a));
        items.add(new item("Drools", "250", R.drawable.b));
        items.add(new item("Trendline", "220", R.drawable.c));
        items.add(new item("Chappi", "150", R.drawable.d));
        items.add(new item("BlackHawk", "270", R.drawable.e));
        items.add(new item("Eukanuba", "300", R.drawable.f));
        items.add(new item("Pedigree", "500", R.drawable.g));
        items.add(new item("SmartHeart", "250", R.drawable.h));

        // Initialize RecyclerView and Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, items, false, false, false, new MyAdapter.OnTotalValueChangeListener() {
            @Override
            public void onTotalValueChanged(double totalValue) {

            }
        });
        recyclerView.setAdapter(adapter);

        // Handle the add button clicks
        adapter.setOnAddClickListener(new MyAdapter.OnAddClickListener() {
            @Override
            public void onAddClick(item item) {
                boolean itemExists = false;
                for (item addedItem : addedItems) {
                    if (addedItem.getName().equals(item.getName())) {
                        addedItem.setQuantity(addedItem.getQuantity() + 1); // Increment quantity
                        itemExists = true;
                        break;
                    }
                }
                if (!itemExists) {
                    addedItems.add(item);
                }
                adapter.notifyDataSetChanged();

                // Show a Toast message
                Toast.makeText(Cart.this, "Item added to the cart", Toast.LENGTH_SHORT).show();
            }
        });

        // Search functionality
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Sorting functionality
        sortRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.highPriceRadioButton) {
                    sortItemsByPrice(true);
                } else if (checkedId == R.id.lowPriceRadioButton) {
                    sortItemsByPrice(false);
                }
            }
        });


        // footer section
        footerWelcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, MainActivity.class);
                startActivity(intent);
            }
        });

        footerCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, AddedItemsActivity.class);
                startActivity(intent);
            }
        });

        articlesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, articleTypeActivity.class);
                startActivity(intent);
            }
        });
    }

    // Filter items based on search input
    private void filter(String text) {
        List<item> filteredList = new ArrayList<>();
        for (item item : items) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    // Sort items by price
    private void sortItemsByPrice(boolean highToLow) {
        if (highToLow) {
            items.sort((item1, item2) -> Integer.compare(Integer.parseInt(item2.getPrice()), Integer.parseInt(item1.getPrice())));
        } else {
            items.sort((item1, item2) -> Integer.compare(Integer.parseInt(item1.getPrice()), Integer.parseInt(item2.getPrice())));
        }
        adapter.notifyDataSetChanged(); // Refresh the RecyclerView with sorted data
    }
}
