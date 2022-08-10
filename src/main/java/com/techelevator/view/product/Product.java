package com.techelevator.view.product;

public abstract class Product {
    private String itemLocation;
    private String itemName;
    private double price;

    public Product(String itemLocation, String itemName, double price) {
        this.itemLocation = itemLocation;
        this.itemName = itemName;
        this.price = price;
    }

    public abstract String getProductType();

    public abstract void getProductMessage();

    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
