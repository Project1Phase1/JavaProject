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

import java.io.Serializable;

import com.customers.Customer;
import com.transactions.Transaction;

public abstract class Account implements Serializable {
	private static final long serialVersionUID = 1777611210940742188L;
	private String accountNumber;
	private double accountBalance;
	
	private Customer customer;
	private Transaction transaction;
	public Account(String accountNumber, double accountBalance, Customer customer, Transaction transaction){
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.customer = customer;
		this.transaction = transaction;
		this.customer.setActive(true);
	}
	
	// get
	
	/** get account number<br><br>
	 * 
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	/** get account balance<br><br>
	 * 
	 * @return the accountBalance
	 */
	public double getAccountBalance() {
		return this.accountBalance;
	}
	
	/** get customer<br><br>
	 * 
	 * @return the customer
	 */
	public Customer getCustomer() {
		return this.customer;
		
	}
	
	/** get transaction<br><br>
	 * 
	 * @return transaction
	 */
	public Transaction getTransaction() {
		return this.transaction;
	}
	// set 
	
	/** set account number<br><br>
	 * 
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	/** set account balance<br><br>
	 * 
	 * @param accountBalance the accountBalance to set
	 */
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	/** set customer<br><br>
	 * 
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/** set transaction<br><br>
	 * 
	 * @param transaction the transactions
	 */
	public void setTransaciton(Transaction transaction) {
		this.transaction = transaction;
	}
	
	// abstract methods
	
	abstract public boolean makeDeposit(double amount);
	
	abstract public double makeWithdrawal(double amount);
	
}
