package com.techelevator.view.product;

public class Candy extends Product{
    public Candy(String itemLocation, String itemName, double price) {
        super(itemLocation, itemName, price);
    }

    @Override
    public String getProductType() {
        return "Candy";
    }

    @Override
    public void getProductMessage() {
        System.out.println("Munch Munch, Yum");
    }

}
