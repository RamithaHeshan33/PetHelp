package com.example.pethelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyVeiwHolder> {

    Context context;
    List<item> items;
    OnAddClickListener onAddClickListener;
    boolean hideAddButton;
    boolean showQuantity;
    boolean showDeleteButton;
    OnTotalValueChangeListener onTotalValueChangeListener; // Callback interface

    // Updated constructor to include showDeleteButton
    public MyAdapter(Context context, List<item> items, boolean hideAddButton, boolean showQuantity, boolean showDeleteButton, OnTotalValueChangeListener onTotalValueChangeListener) {
        this.context = context;
        this.items = items;
        this.hideAddButton = hideAddButton;
        this.showQuantity = showQuantity;
        this.showDeleteButton = showDeleteButton;
        this.onTotalValueChangeListener = onTotalValueChangeListener;
    }

    @NonNull
    @Override
    public MyVeiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_view, parent, false);
        return new MyVeiwHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVeiwHolder holder, int position) {
        item currentItem = items.get(position);

        holder.nameView.setText(currentItem.getName());
        holder.priceView.setText(currentItem.getPrice());
        holder.imageView.setImageResource(currentItem.getImage());

        // Show or hide the "Add" button
        if (hideAddButton) {
            holder.addButton.setVisibility(View.GONE);
        } else {
            holder.addButton.setVisibility(View.VISIBLE);
            holder.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAddClickListener != null) {
                        onAddClickListener.onAddClick(currentItem);
                    }
                }
            });
        }

        // Show or hide the quantity and '-' button
        if (showQuantity) {
            holder.quantityView.setVisibility(View.VISIBLE);
            holder.decreaseQuantityButton.setVisibility(View.VISIBLE);
            holder.quantityView.setText("Quantity: " + currentItem.getQuantity());

            holder.decreaseQuantityButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentQuantity = currentItem.getQuantity();
                    if (currentQuantity > 1) {
                        currentItem.setQuantity(currentQuantity - 1);
                        holder.quantityView.setText("Quantity: " + currentItem.getQuantity());
                        updateTotalValue(); // Update total value
                    }
                }
            });

            updateTotalValue(); // Initial total value calculation
        } else {
            holder.quantityView.setVisibility(View.GONE);
            holder.decreaseQuantityButton.setVisibility(View.GONE);
        }

        // Show or hide the delete button
        if (showDeleteButton) {
            holder.deleteButton.setVisibility(View.VISIBLE);
            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    items.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    notifyItemRangeChanged(holder.getAdapterPosition(), items.size());
                    updateTotalValue();

                    // Show a Toast message
                    Toast.makeText(v.getContext(), "Item is removed from the cart", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            holder.deleteButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Method to update the list and notify the RecyclerView
    public void filterList(List<item> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
        updateTotalValue(); // Update total when filtering
    }

    // Update total value and notify the activity
    private void updateTotalValue() {
        double total = 0;
        for (item item : items) {
            total += item.getQuantity() * Double.parseDouble(item.getPrice());
        }
        if (onTotalValueChangeListener != null) {
            onTotalValueChangeListener.onTotalValueChanged(total);
        }
    }

    public void setOnAddClickListener(OnAddClickListener listener) {
        this.onAddClickListener = listener;
    }

    public interface OnAddClickListener {
        void onAddClick(item item);
    }

    // Interface to handle total value changes
    public interface OnTotalValueChangeListener {
        void onTotalValueChanged(double total);
    }
}
