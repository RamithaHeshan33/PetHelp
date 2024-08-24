package com.example.pethelp;

public class item {
    private String name;
    private String price;
    private int image;
    private int quantity; // Add quantity field

    public item(String name, String price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = 1; // Initialize quantity to 1
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
