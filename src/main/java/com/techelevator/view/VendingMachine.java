package com.techelevator.view;

import com.techelevator.view.logger.Log;
import com.techelevator.view.logger.SalesLog;

import com.techelevator.view.product.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

public class VendingMachine {
    Scanner userInput = new Scanner(System.in);
    private Map<Product,Integer> inventory = new LinkedHashMap<>();
    private BigDecimal balance = BigDecimal.valueOf(0.00);

    public VendingMachine() throws FileNotFoundException {
      getProductFromFile();
      Log.logLineSeparator();
      Log.logEvent("Vending machine started.");
      Log.logLineSeparator();
    }
    public Product getProductFromFile() throws FileNotFoundException {
        File dataFile = new File("vendingmachine.csv");
        String[] currentLine;
        String productType;
        try(Scanner readInventoryFile = new Scanner(dataFile)){

            while(readInventoryFile.hasNextLine()) {
                currentLine = readInventoryFile.nextLine().split("\\|");
                productType = currentLine[3];
                switch (productType) {
                    case "Chip":
                        inventory.put(new Chips(currentLine[0], currentLine[1], Double.parseDouble(currentLine[2])), 5);
                        break;
                    case "Candy":
                        inventory.put(new Candy(currentLine[0], currentLine[1], Double.parseDouble(currentLine[2])), 5);
                        break;
                    case "Drink":
                        inventory.put(new Beverages(currentLine[0], currentLine[1], Double.parseDouble(currentLine[2])), 5);
                        break;
                    case "Gum":
                        inventory.put(new Gum(currentLine[0], currentLine[1], Double.parseDouble(currentLine[2])), 5);
                        break;
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        return null;
    }
    public void displayItems(){
        String productName;
        String productLocation;
        String productPrice;
        String productType;
        int quantityLeft;
        for(Map.Entry<Product,Integer> item:inventory.entrySet()) {
            productName = item.getKey().getItemName();
            productLocation = item.getKey().getItemLocation();
            productPrice = String.valueOf(item.getKey().getPrice());
            productType = item.getKey().getProductType();
            quantityLeft = item.getValue();
            if (quantityLeft == 0) {
                System.out.println(productLocation + " " + productName + " " + productPrice + " " + productType + " SOLD OUT");
            }else {
                System.out.println(productLocation + " " + productName + " " + productPrice + " " + productType + " " + quantityLeft);
            }
        }
    }
    public void currentMoney() {
        System.out.printf("\nCurrent money provided: $%.2f\n", balance);
    }
    public void updateBalance(double userMoney){
        BigDecimal balanceBeforeInput = balance;
        try {
            final List<Double> acceptedBills = new ArrayList<>(Arrays.asList(1.0,2.0,5.0,10.0));
            if(acceptedBills.contains(userMoney)) {
                balance = balance.add(BigDecimal.valueOf(userMoney));
            }else{
                System.out.println("This machine only accepts $1, $2, $5, $10");
            }
        }catch(NumberFormatException e){
            System.out.println("This machine only accepts $1, $2, $5, $10");
        }
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        String balanceBefore = numberFormat.format(balanceBeforeInput);
        String balanceAfter = numberFormat.format(balance);
        Log.logEvent("FEED MONEY: " + balanceBefore + " --> " + balanceAfter);
    }
//    public void purchaseItem() {
//        String productLocation;
//        System.out.println("Please select item location.");
//        productLocation = userInput.nextLine();
//        Product productToBuy = returnProductByLocation(productLocation);
//        if(productToBuy != null) {
//            if(balance >= productToBuy.getPrice()){
//                inventory.put()
//            }
//        }
//    }
    public void purchaseItem(String productLocation) {
        boolean foundItem = false;
        BigDecimal balanceBeforePurchase = balance;
        for(Map.Entry<Product,Integer> item: inventory.entrySet()) {
            if(item.getKey().getItemLocation().equalsIgnoreCase(productLocation)) {
                foundItem = true;
               if(item.getValue() != 0) {
                   if(balance.doubleValue() >= item.getKey().getPrice()) {
                       inventory.put(item.getKey(), item.getValue() - 1);
                       balance = balance.subtract(BigDecimal.valueOf(item.getKey().getPrice()));
                       System.out.printf("%s $%.2f Money Remaining: $%.2f\n",item.getKey().getItemName(),item.getKey().getPrice(),balance);
                       item.getKey().getProductMessage();
                       NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
                       String balanceBefore = numberFormat.format(balanceBeforePurchase);
                       String balanceAfter = numberFormat.format(balance);
                       Log.logEvent(item.getKey().getItemName() + " " + item.getKey().getItemLocation() + " " + balanceBefore + " --> " + balanceAfter);
                   }else{
                       System.out.println("You don't have enough money to purchase this item.");
                   }
               }else{
                   System.out.println("This item is sold out.");
               }
            }
        }
        if(!foundItem){
            System.out.println("Invalid product location.");
        }
    }
    public void returnChange() {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        while(balance.doubleValue()/0.25 >= 1) {
            quarters++;
            balance = balance.subtract(BigDecimal.valueOf(0.25));
        }
        while(balance.doubleValue()/0.10 >= 1) {
            dimes++;
            balance = balance.subtract(BigDecimal.valueOf(0.10));
        }
        while(balance.doubleValue()/0.05 >= 1) {
            nickels++;
            balance = balance.subtract(BigDecimal.valueOf(0.05));
        }
        System.out.printf("You recieved %d quarters, %d dimes, %d nickels back from the vending machine.%n",quarters,dimes,nickels);
    }
    public void generateReport() {
        int quantitySold;
        String productName;
        SalesLog.logLineSeparator();
        for(Map.Entry<Product,Integer> product :inventory.entrySet()){
            productName = product.getKey().getItemName();
            quantitySold = 5 - product.getValue();
            SalesLog.logEvent(productName + " | " + quantitySold + " | SOLD");
        }
    }

    public double getBalance() {
        return balance.doubleValue();
    }

    public Map<Product, Integer> getInventory() {
        return inventory;
    }
}
