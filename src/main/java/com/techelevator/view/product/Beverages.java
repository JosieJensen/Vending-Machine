package com.techelevator.view.product;

public class Beverages extends Product{
    public Beverages(String itemLocation, String itemName, double price) {
        super(itemLocation, itemName, price);
    }

    @Override
    public String getProductType() {
        return "Drink";
    }

    @Override
    public void getProductMessage() {
        System.out.println("Glug Glug, Yum!");
    }

}
