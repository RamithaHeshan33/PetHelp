package com.example.pethelp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class MyVeiwHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView, priceView, quantityView;
    Button addButton, decreaseQuantityButton;
    Button deleteButton;

    public MyVeiwHolder(@NotNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        nameView = itemView.findViewById(R.id.name);
        priceView = itemView.findViewById(R.id.price);
        addButton = itemView.findViewById(R.id.add_button);
        decreaseQuantityButton = itemView.findViewById(R.id.decreaseQuantityBtn);
        quantityView = itemView.findViewById(R.id.quantityView);
        deleteButton = itemView.findViewById(R.id.deleteButton);
    }
}
