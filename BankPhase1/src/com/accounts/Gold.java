/**
 * @mentor Professor Dr. Awny Alnusair<br><br>
 * @college Indiana University Kokomo<br><br><br>
 * @course INFO-211 Informatics II<br><br>
 * 
 * 
 * @authors Ian Holtson<br>Jeremiah McKinney<br>Theral Jessop<br>
 * Apr 6, 2015<br>
 * Banker.java<br>
 *
 */
package com.accounts;

import com.customers.Customer;

public class Gold extends Account {
	private double goldInterestAmount;
	private double goldInterestRate;
	
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

	public boolean makeWithdrawal(double amount) {
		if (amount < 0) {
			return false;
		}
		this.setAccountBalance(this.getAccountBalance() - amount);
		return true;
	}

	public String toString() {
		String 
		output =  "\n===========================================================\n";
		output += "                        Gold Account\n";
		output += "-------------------------------------------------------\n";
		output += "Customer:" + this.getCustomer() + "\n";
		output += "-------------------------------------------------------\n";
		output += "Account Number: " + this.getAccountNumber() + "\n";
		output += "Account Balance: $" + this.getAccountBalance() + "\n";
		output += "Interest Rate: " + this.goldInterestRate + "%\n";
		output += "Interest Earned (ytd): $" + this.goldInterestAmount + "\n";
		output += "\n===========================================================\n\n";
		return output;
	}
}
