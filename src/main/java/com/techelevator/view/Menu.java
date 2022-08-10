package com.techelevator.view;

public class Menu {
    private static final String MAIN_MENU_DISPLAY_ITEMS = "Display vending machine items";
    private static final String MAIN_MENU_PURCHASE = "Purchase";
    private static final String MAIN_MENU_EXIT = "Exit";
    private static final String[] MAIN_MENU = new String[]{MAIN_MENU_DISPLAY_ITEMS,MAIN_MENU_PURCHASE,MAIN_MENU_EXIT};
    private static final String PURCHASE_MENU_FEED_MONEY = "Feed money";
    private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select product";
    private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish transaction";
    private static final String[] PURCHASE_MENU = new String[]{PURCHASE_MENU_FEED_MONEY,PURCHASE_MENU_SELECT_PRODUCT,PURCHASE_MENU_FINISH_TRANSACTION};

    public static void printMainMenu() {
        int lineNumber = 1;
        for(String selection:MAIN_MENU){
            System.out.println(lineNumber + ") " + selection);
            lineNumber++;
        }
    }
    public static void printPurchaseMenu() {
        int lineNumber = 1;
        for(String selection:PURCHASE_MENU){
            System.out.println(lineNumber + ") " + selection);
            lineNumber++;
        }

    }
}
