package com.techelevator.view;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;



import static org.assertj.core.api.Assertions.assertThat;



class VendingmachineApplicationTests {
	VendingMachine testing;

	@BeforeEach
	void setUp() throws FileNotFoundException {
		testing = new VendingMachine();
	}

	@Test
	void updateBalanceNoNegatives() {
		testing.updateBalance(5.00);
		double expectedBalance = 5.00;
		double actualBalance = testing.getBalance();

		assertThat(expectedBalance).isEqualTo(actualBalance);
	}

	@Test
	void insertMoneyNoNegatives() {
		testing.updateBalance(5.00);
		testing.updateBalance(-2.00);
		double expectedBalance = 5.00;
		double actualBalance = testing.getBalance();

		assertThat(expectedBalance).isEqualTo(actualBalance);
	}

	@Test
	void acceptedBills() {
		testing.updateBalance(10.00);
		testing.updateBalance(3.00);
		double expectedBalance = 10.00;
		double actualBalance = testing.getBalance();

		assertThat(expectedBalance).isEqualTo(actualBalance);
	}

	@Test
	void returnToUserMakesBalanceZero() {
		testing.updateBalance(5.00);
		testing.updateBalance(1.00);
		testing.returnChange();
		double expectedBalance = 0.00;
		double actualBalance = testing.getBalance();

		assertThat(expectedBalance).isEqualTo(actualBalance);
	}

	@Test
	void purchaseProductUpdateBalance() {
		testing.updateBalance(5.00);
		testing.purchaseItem("A1");
		double expectedBalance = 1.95;
		double actualBalance = testing.getBalance();

		assertThat(expectedBalance).isEqualTo(actualBalance);
	}

	@Test
	void purchaseInvalidProduct() {
		testing.updateBalance(5.00);
		testing.purchaseItem("afgjkasfja");
		double expectedBalance = 5.00;
		double actualBalance = testing.getBalance();

		assertThat(expectedBalance).isEqualTo(actualBalance);
	}

	@Test
	void soldOutProduct() {
		testing.updateBalance(5.00);
		for (int i = 0; i <= 5; i++) {
			testing.purchaseItem("D3");
		}
		double expectedBalance = 1.25;
		double actualBalance = testing.getBalance();

		assertThat(expectedBalance).isEqualTo(actualBalance);
	}

	@Test
	void productToExpensive() {
		testing.updateBalance(1.00);
		testing.purchaseItem("A1");
		double expectedBalance = 1.00;
		double actualBalance = testing.getBalance();

		assertThat(expectedBalance).isEqualTo(actualBalance);
	}


}
