package com.techelevator.view.product;

public class Gum extends Product{
    public Gum(String itemLocation, String itemName, double price) {
        super(itemLocation, itemName, price);
    }

    @Override
    public String getProductType() {
        return "Gum";
    }

    @Override
    public void getProductMessage() {
        System.out.println("Chew Chew, Yum!");
    }

}
