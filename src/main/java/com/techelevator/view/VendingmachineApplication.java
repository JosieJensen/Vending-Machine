package com.techelevator.view;

import com.techelevator.view.logger.Log;
import com.techelevator.view.logger.SalesLog;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class VendingmachineApplication {
	static private Scanner userinput = new Scanner(System.in);
	static VendingMachine vendoMatic800;

	static {
		try {
			vendoMatic800 = new VendingMachine();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		run();

	}
	public static void run(){
		while(true){
			int userChoice;
			Menu.printMainMenu();
			System.out.println("Please make a selection.");
			userChoice = getUserInputMainMenu();
			mainMenuOption(userChoice);
		}
	}
	// Displays main menu
	public static void mainMenuOption(int userInput) {
		switch(userInput) {
			case 1:
				vendoMatic800.displayItems();
				break;
			case 2:
				Menu.printPurchaseMenu();
				vendoMatic800.currentMoney();
				purchaseMenuOption(getUserInputMainMenu());
				break;
			case 3:
				exitvendingMachine();
			case 4:
				salesReport();
				exitvendingMachine();
		}
	}
	// Generates sales report to salesLog.txt
	private static void salesReport() {
		System.out.println("Please enter password to generate report: ");
		String password = userinput.nextLine();
		if(password.equalsIgnoreCase("password")){
			vendoMatic800.generateReport();
		}else{
			System.out.println("Incorrect password.");
		}
	}
	// Displays purchase menu
	public static void purchaseMenuOption(int userInput) {
		switch(userInput) {
			case 1:
				vendoMatic800.updateBalance(insertMoney());
				break;
			case 2:
				vendoMatic800.displayItems();
				vendoMatic800.purchaseItem(purchaseItem());
				break;
			case 3:
				exitvendingMachine();
		}
	}
	// Accepts item location
	private static String purchaseItem() {
		System.out.println("Please select item location.");
		return userinput.nextLine();
	}

	private static double insertMoney() {
		double userMoneyInput;
		System.out.println("How much money would you like to insert?");
		try{
			userMoneyInput = Double.parseDouble(userinput.nextLine());
			return userMoneyInput;
		}catch (NumberFormatException e){
			return -1;
		}
	}

	private static void exitvendingMachine() {
		vendoMatic800.returnChange();
		System.out.println("Thank you for using the vending machine.");
		Log.closePrintWriter();
		SalesLog.closePrintWriter();
		System.exit(0);
	}

	public static int getUserInputMainMenu() {
		try{
			return Integer.parseInt(userinput.nextLine());
		}catch (NumberFormatException e){
			System.out.println("Invalid, Please choose 1,2,3.");
		}
		return -1;
	}

}
