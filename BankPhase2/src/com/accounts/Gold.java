/**
 * @mentor Professor Dr. Awny Alnusair<br><br>
 * @college Indiana University Kokomo<br><br><br>
 * @course INFO-211 Informatics II<br><br>
 * 
 * 
 * @authors Ian Holtson<br>Jeremiah McKinney<br>Theral Jessop<br>
 * Apr 6, 2015<br>
 * Gold.java<br>
 *
 */
package com.accounts;

import com.customers.Customer;

public class Gold extends Account {
	private double goldInterestAmount;
	private double goldInterestRate;
	
	public Gold() {
		
	}
	
	/**
	 * @param accountNumber
	 * @param accountBalance
	 * @param customer
	 */
	public Gold(String accountNumber, double accountBalance, Customer customer) {
		super(accountNumber, accountBalance, customer);
		this.goldInterestAmount = 0.0;
		this.goldInterestRate = 5.0;

	}

	public Gold(String accountNumber, double accountBalance, Customer customer, double goldInterestAmount, double goldInterestRate) {
		super(accountNumber, accountBalance, customer);
		this.goldInterestAmount = goldInterestAmount;
		this.goldInterestRate = goldInterestRate;
	}
	
	
	/**
	 * @return the goldInterestAmount
	 */
	public double getGoldInterestAmount() {
		return goldInterestAmount;
	}



	/**
	 * @return the goldInterestRate
	 */
	public double getGoldInterestRate() {
		return goldInterestRate;
	}



	/**
	 * @param goldInterestAmount the goldInterestAmount to set
	 */
	public void setGoldInterestAmount(double goldInterestAmount) {
		this.goldInterestAmount = goldInterestAmount;
	}



	/**
	 * @param goldInterestRate the goldInterestRate to set
	 */
	public void setGoldInterestRate(double goldInterestRate) {
		if (goldInterestRate < 0) {
			System.out.print("\nInvalid Gold interest rate! Reseting to default value!\n");
			goldInterestRate = 5.0;
		} else if (goldInterestRate > 0.0 && goldInterestRate < 1.0) {
			goldInterestRate *= 100;
		}
		this.goldInterestRate = goldInterestRate;
		System.out.print("\nGold interest rate successfully changed!\n");
	}



	public boolean makeDeposit(double amount) {
		if (amount < 0) {
			return false;
		}
		this.setAccountBalance(this.getAccountBalance() + amount);
		return true;
	}

	public double makeWithdrawal(double amount) {
		if (amount < 0) {
			return -1.0;
		}
		this.setAccountBalance(this.getAccountBalance() - amount);
		return amount;
	}

	public String toString() {
			return String.format("%12s %-55s %-15s %12.2f %s %10.2f%% %3s %12.2f\n", "Gold", this.getCustomer(), this.getAccountNumber(), this.getAccountBalance(), "", this.getGoldInterestRate(), "", this.getGoldInterestAmount());
	}
}
/*
		System.out.print("\n===========================================================\n");
		System.out.print("                        Gold Account\n");
		System.out.print("-------------------------------------------------------\n");
		System.out.print("Customer:" + this.getCustomer() + "\n");
		System.out.print("-------------------------------------------------------\n");
		System.out.print("Account Number: " + this.getAccountNumber() + "\n");
		System.out.printf("%s %12.2f %s", "Account Balance: $", this.getAccountBalance(), "\n");
		System.out.print("Interest Rate: " + this.goldInterestRate + "%\n");
		System.out.printf("%s %12.2f %s", "Interest Earned (ytd): $", this.goldInterestAmount, "\n");
		System.out.print("\n===========================================================\n\n");
*/