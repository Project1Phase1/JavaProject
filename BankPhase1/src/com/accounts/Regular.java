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

public class Regular extends Account {
	private double regularInterestRate;
	private double regularInterestAmount;
	private double regularFixedCharge;
	
	/**
	 * @param accountNumber
	 * @param accountBalance
	 * @param customer
	 */
	public Regular(String accountNumber, double accountBalance,	Customer customer) {
		super(accountNumber, accountBalance, customer);
		this.regularInterestRate = 6.0;
		this.regularInterestAmount = 0.0;
		this.regularFixedCharge = 10.0;
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



	public String makeDeposit(double amount) {
		String output = "";
		if (amount < 0) {
			output += "\nInvalid Regular deposit amount! Regular depost was unsuccessful!\n";
			return output;
		}
		this.setAccountBalance(this.getAccountBalance() + amount);
		output += "Regular deposit was successfully applied! Added $" + amount + "\n";
		return output;
	}

	public String makeWithdrawal(double amount) {
		String output = "";
		if (amount < 0) {
			output += "Invalid Regular withdrawal amount! Regular withdrawal was unsuccessful!\n";
			return output;
		} else if (this.getAccountBalance() < amount) {
			output += "Insufficient funds! Withdrawing available funds ONLY!\n";
			amount = this.getAccountBalance();
		}
		this.setAccountBalance(this.getAccountBalance() - amount);
		output += "Regular withdrawal successful! Withdrew $" + amount + "\n";
		return output;
	}

	public String toString() {
		String 
		output =  "\n===========================================================\n";
		output += "                      <strong>Regular Account</strong>\n";
		output += "---------------------------------------\n";
		output += "Customer: " + this.getCustomer() + "\n";
		output += "---------------------------------------\n";
		output += "Account Number: " + this.getAccountNumber() + "\n";
		output += "Account Balance: " + this.getAccountBalance() + "\n";
		output += "Interest Rate: " + this.regularInterestRate + "%\n";
		output += "Interest Fee: $" + this.regularFixedCharge + "\n";
		output += "\nInterest Earned: $" + this.regularInterestAmount;
		output += "\n===========================================================\n\n";
		return output;
	}

}
