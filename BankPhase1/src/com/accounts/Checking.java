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

public class Checking extends Account {
	private double checkingTransactionFee;
	private double checkingTransactionFeeAmount;
	private int numberOfTransactions;
	
	/** checking constructor non-default
	 * 
	 * @param accountNumber String
	 * @param accountBalance double
	 * @param customer Customer
	 */
	public Checking(String accountNumber, double accountBalance,
			Customer customer) {
		super(accountNumber, accountBalance, customer);
		this.checkingTransactionFee = 3.00;
		this.checkingTransactionFeeAmount = 0.0;
		this.numberOfTransactions = 0;
	}

	// get
	
	/**
	 * @return the checkingTransactionFee
	 */
	public double getCheckingTransactionFee() {
		return checkingTransactionFee;
	}

	/**
	 * @return the checkingTransactionFeeAmount
	 */
	public double getCheckingTransactionFeeAmount() {
		return checkingTransactionFeeAmount;
	}

	/**
	 * @return the numberOfTransactions
	 */
	public int getNumberOfTransactions() {
		return numberOfTransactions;
	}

	// set

	/**
	 * @param checkingTransactionFee the checkingTransactionFee to set
	 */
	public void setCheckingTransactionFee(double checkingTransactionFee) {
		this.checkingTransactionFee = checkingTransactionFee;
	}

	/**
	 * @param checkingTransactionFeeAmount the checkingTransactionFeeAmount to set
	 */
	public void setCheckingTransactionFeeAmount(double checkingTransactionFeeAmount) {
		this.checkingTransactionFeeAmount = checkingTransactionFeeAmount;
	}
	
	/**
	 * 
	 * @param numberOfTransactions the numberOfTransactions to set
	 */
	public void setNumberOfTransactions(int numberOfTransactions) {
		this.numberOfTransactions = numberOfTransactions;
	}

	public boolean makeDeposit(double amount) {
		if (amount < 0) {
			return false;
		}
		this.numberOfTransactions++;
		if (this.numberOfTransactions > 2) {
			this.checkingTransactionFeeAmount += this.checkingTransactionFee;
		}
		this.setAccountBalance(this.getAccountBalance() + amount);
		return true;
	}

	public boolean makeWithdrawal(double amount) {
		if (amount < 0) {
			return false;
		}
		this.numberOfTransactions++;
		if (this.numberOfTransactions > 2) {
			this.checkingTransactionFeeAmount += this.checkingTransactionFee;
		}
		if (this.getAccountBalance() < amount) {
			amount = this.getAccountBalance();
			}
		this.setAccountBalance(this.getAccountBalance() - amount);
		return true;
	}
	
	public String toString() {
		String 
		output =  "\n===========================================================\n";
		output += "                      Checking Account\n";
		output += "-------------------------------------------------------\n";
		output += "Customer:" + this.getCustomer() + "\n";
		output += "-------------------------------------------------------\n";
		output += "Account Number: " + this.getAccountNumber() + "\n";
		output += "Account Balance: $" + this.getAccountBalance() + "\n";
		output += "Number of Transactions: " + (((this.numberOfTransactions - 2)<0)? this.numberOfTransactions : (this.numberOfTransactions-2))+ "\n";
		output += "Transaction Fee: $" + this.checkingTransactionFee + "\n";
		output += "Transaction Fee Amount (this month): $" + this.checkingTransactionFeeAmount + "\n";
		output += "\n===========================================================\n\n";
		return output;
	}
}
