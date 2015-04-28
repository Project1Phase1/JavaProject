/**
 * @mentor Professor Dr. Awny Alnusair<br><br>
 * @college Indiana University Kokomo<br><br><br>
 * @course INFO-211 Informatics II<br><br>
 * 
 * 
 * @authors Ian Holtson<br>Jeremiah McKinney<br>Theral Jessop<br>
 * Apr 6, 2015<br>
 * Regular.java<br>
 *
 */
package com.accounts;

import com.customers.Customer;

public class Regular extends Account {
	private static final long serialVersionUID = 8082267220118547343L;
	private double regularInterestRate;
	private double regularInterestAmount;
	private double regularFixedCharge;
	
	/** complete constructor to create the regular account with user values<br><br>
	 * 
	 * @param accountNumber the account number for the account
	 * @param accountBalance the opening balance of the account
	 * @param customer the owner of the account
	 * @param regularInterestRate the interest rate in percent form
	 * @param regularInterestAmount the amount of interest calculated so far
	 * @param regularFixedCharge the amount of the fixed charge when interest is calculated
	 * 
	 */
	public Regular() {
		
	}
	
	/** basic constructor to create the regular account with default values<br><br>
	 * 
	 * @param accountNumber the account number for the account
	 * @param accountBalance the opening balance of the account
	 * @param customer the owner of the account
	 * 
	 */
	public Regular(String accountNumber, double accountBalance,	Customer customer) {
		super(accountNumber, accountBalance, customer);
		this.regularInterestRate = 6.0;
		this.regularInterestAmount = 0.0;
		this.regularFixedCharge = 10.0;
	}
	
	/** complete constructor to create the regular account with user values<br><br>
	 * 
	 * @param accountNumber the account number for the account
	 * @param accountBalance the opening balance of the account
	 * @param customer the owner of the account
	 * @param regularInterestRate the interest rate in percent form
	 * @param regularInterestAmount the amount of interest calculated so far
	 * @param regularFixedCharge the amount of the fixed charge when interest is calculated
	 * 
	 */
	public Regular(String accountNumber, double accountBalance, Customer customer, double regularInterestRate, double regularInterestAmount, double regularFixedCharge) {
		super(accountNumber, accountBalance, customer);
		this.regularInterestRate = regularInterestRate;
		this.regularInterestAmount = regularInterestAmount;
		this.regularFixedCharge = regularFixedCharge;
	}
	
	// get
	
	/**
	 * @return the regularInterestRate
	 */
	public double getRegularInterestRate() {
		return regularInterestRate;
	}



	/**
	 * @return the regularInterestAmount
	 */
	public double getRegularInterestAmount() {
		return regularInterestAmount;
	}



	/**
	 * @return the regularFixedCharge
	 */
	public double getRegularFixedCharge() {
		return regularFixedCharge;
	}

	// set

	/**
	 * @param regularInterestRate the regularInterestRate to set
	 */
	public void setRegularInterestRate(double regularInterestRate) {
		if (regularInterestRate < 0) {
			System.out.print("Invalid Regular interest rate! Reseting to default value\n");
			regularInterestRate = 6.0;
		} else if (regularInterestRate > 0. && regularInterestRate < 1.0) {
			regularInterestRate *= 100;
		}
		this.regularInterestRate = regularInterestRate;
		System.out.print("Regular interest rate successfully changed!");
	}



	/**
	 * @param regularFixedCharge the regularFixedCharge to set
	 */
	public void setRegularFixedCharge(double regularFixedCharge) {
		if (regularFixedCharge < 0) {
			System.out.print("\nInvalid Regular interest charge! Reseting to original value\n");
			regularFixedCharge = 10.0;
		}
		this.regularFixedCharge = regularFixedCharge;
		System.out.print("Regular interest charge successfully updated!\n");
	}

	public void setRegularInterestAmount(double interest) {
		this.regularInterestAmount += interest;
	}

	/** make deposit<br><br>
	 * 
	 * @param amount the amount of the deposit
	 * @return true or false<br>was the deposit successful or not
	 * 
	 */
	public boolean makeDeposit(double amount) {
		if (amount < 0) {
			return false;
		}
		this.setAccountBalance(this.getAccountBalance() + amount);
		return true;
	}

	/** make withdrawal<br><br>
	 * 
	 * @param amount the amount to withdraw
	 * @return true or false<br>was the withdrawal successful or not
	 * 
	 */
	public double makeWithdrawal(double amount) {
		if (amount < 0) {
			return -1.0;
		} else if (this.getAccountBalance() < amount) {
			amount = this.getAccountBalance();
		}
		this.setAccountBalance(this.getAccountBalance() - amount);
		return amount;
	}

	public String toString() {
		return String.format("%12s %-55s %-15s %12.2f %s %10.2f%% %3s %12.2f %6s %12.2f\n", "Regular", this.getCustomer(), this.getAccountNumber(), this.getAccountBalance(), "", this.getRegularInterestRate(), "", this.getRegularFixedCharge(), "", this.getRegularInterestAmount());
	}

}
/*
		System.out.print("\n===========================================================\n");
		System.out.print("                      Regular Account\n");
		System.out.print("-------------------------------------------------------\n");
		System.out.print("Customer:" + this.getCustomer() + "\n");
		System.out.print("-------------------------------------------------------\n");
		System.out.print("Account Number: " + this.getAccountNumber() + "\n");
		System.out.print("Account Balance: $" + this.getAccountBalance() + "\n");
		System.out.print("Interest Rate: " + this.regularInterestRate + "%\n");
		System.out.print("Interest Fee: $" + this.regularFixedCharge + "\n");
		System.out.print("\nInterest Earned (ytd): $" + this.regularInterestAmount);
		System.out.print("\n===========================================================\n\n");
*/