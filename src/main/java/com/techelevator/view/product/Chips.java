package com.techelevator.view.product;

public class Chips extends Product{
    public Chips(String itemLocation, String itemName, double price) {
        super(itemLocation, itemName, price);
    }

    @Override
    public String getProductType() {
        return "Chips";
    }

    @Override
    public void getProductMessage() {
        System.out.println("Crunch Crunch, Yum");
    }

}
