/**
 * @mentor Professor Dr. Awny Alnusair<br><br>
 * @college Indiana University Kokomo<br><br><br>
 * @course INFO-211 Informatics II<br><br>
 * 
 * 
 * @authors Ian Holtson<br>Jeremiah McKinney<br>Theral Jessop<br>
 * Apr 6, 2015<br>
 * Checking.java<br>
 *
 */
package com.accounts;

import com.customers.Customer;

public class Checking extends Account {
	private static final long serialVersionUID = -8807798234473395749L;
	private double checkingTransactionFee;
	private double checkingTransactionFeeAmount;
	private int numberOfTransactions;
	/** all parameter constructor
	 * 
	 * @param accountNumber
	 * @param accountBalance
	 * @param customer
	 * @param checkingTransactionFee
	 * @param checkingTransactionFeeAmount
	 */
	public Checking() {
		
	}
	
	/** checking constructor non-default
	 * 
	 * @param accountNumber String
	 * @param accountBalance double
	 * @param customer Customer
	 */
	public Checking(String accountNumber, double accountBalance, Customer customer) {
		super(accountNumber, accountBalance, customer);
		this.checkingTransactionFee = 3.00;
		this.checkingTransactionFeeAmount = 0.0;
		this.numberOfTransactions = 0;
	}

	/** all parameter constructor
	 * 
	 * @param accountNumber
	 * @param accountBalance
	 * @param customer
	 * @param checkingTransactionFee
	 * @param checkingTransactionFeeAmount
	 */
	public Checking(String accountNumber, double accountBalance, Customer customer, double checkingTransactionFee, double checkingTransactionFeeAmount) {
		super(accountNumber, accountBalance, customer);
		this.checkingTransactionFee = checkingTransactionFee;
		this.checkingTransactionFeeAmount = checkingTransactionFeeAmount;
		if (checkingTransactionFeeAmount > 0) {
			this.numberOfTransactions = (int) (checkingTransactionFeeAmount / checkingTransactionFee);
		} else {
			this.numberOfTransactions = 0;
		}
		
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
		this.setNumberOfTransactions(this.getNumberOfTransactions() + 1);
		if (this.numberOfTransactions > 2) {
			this.checkingTransactionFeeAmount += this.checkingTransactionFee;
		}
		this.setAccountBalance(this.getAccountBalance() + amount);
		return true;
	}

	public double makeWithdrawal(double amount) {
		if (amount < 0) {
			return -1.0;
		}
		this.setNumberOfTransactions(this.getNumberOfTransactions() + 1);
		if (this.numberOfTransactions > 2) {
			this.checkingTransactionFeeAmount += this.checkingTransactionFee;
		}
		if (this.getAccountBalance() < amount) {
			amount = this.getAccountBalance();
		}
		this.setAccountBalance((this.getAccountBalance() - amount));
		return amount;
	}
	
	public String toString() {
		return String.format("%12s %-55s %-15s %12.2f %s %10d %4s %12.2f %6s %12.2f\n", "Checking", this.getCustomer(), this.getAccountNumber(), this.getAccountBalance(), "", this.getNumberOfTransactions(), "", this.getCheckingTransactionFee(), "", this.getCheckingTransactionFeeAmount());
		
	}
}
/*
		System.out.print("Customer:" + this.getCustomer() + "\n");
		System.out.print("-------------------------------------------------------\n");
		System.out.print("Account Number: " + this.getAccountNumber() + "\n");
		System.out.printf("%s %12.2f %s", "Account Balance: $", this.getAccountBalance(), "\n");
		System.out.print("Number of Transactions: " + this.numberOfTransactions + "\n");
		System.out.printf("%s %12.2f %s", "Transaction Fee: $", this.checkingTransactionFee, "\n");
		System.out.printf("%s %12.2f %s", "Transaction Fee Amount (this month): $", this.checkingTransactionFeeAmount, "\n");
		System.out.print("\n===========================================================\n\n");
*/