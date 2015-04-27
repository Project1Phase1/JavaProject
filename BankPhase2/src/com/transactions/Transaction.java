package com.transactions;

/** transaction class<br>
 * this class is only to be used with the client side application<br>
 * it tracks all transactions that change the account balance and is<br>
 * used throughout the client code<br>
 * 
 * @mentor and
 * @instructor Dr. Awny Alnusair<br><br>
 * @college Professor at Indiana University Kokomo<br><br><br>
 * @course INFO-211 Informatics II<br><br>
 * 
 * 
 * @authors Ian Holtson<br>Jeremiah McKinney<br>Theral Jessop<br><br>
 * Apr 6, 2015<br>
 * Transaction.java<br>
 *
 */
public class Transaction {
	private java.util.Date createDate;
	private String customerID;
	private String accountNumber;
	private String description;
	private double amount;
	private int transactionNumber;
	
	Transaction() {
		
	}
	
	/** transaction constructor<br>
	 * there is no default constructor for this class<br>
	 * 
	 * @param createDate java.util.Date
	 * @param customerID from the Customer
	 * @param accountNumber from the Account
	 * @param description a description of the transaction (Deposit, Withdraw, EOM calculations, etc.)
	 * @param amount of the transaction
	 * @param transactionNumber a unique transaction number
	 */
	public Transaction(java.util.Date createDate, String customerID, String accountNumber, String description, double amount, int transactionNumber) {
		this.createDate = createDate;
		this.customerID = customerID;
		this.accountNumber = accountNumber;
		this.description = description;
		this.amount = amount;
		this.transactionNumber = transactionNumber;
	}
	
	public Transaction(java.util.Date createDate, String customerID, String description, int transactionNumber) {
		this.createDate = createDate;
		this.customerID = customerID;
		this.accountNumber = "No Accounts";
		this.description = description;
		this.amount = 0.00;
		this.transactionNumber = transactionNumber;
	}

	/**
	 * @return the createDate
	 */
	public java.util.Date getCreateDate() {
		return createDate;
	}

	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the transactionNumber
	 */
	public int getTransactionNumber() {
		return transactionNumber;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param transactionNumber the transactionNumber to set
	 */
	public void setTransactionNumber(int transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	
	public String toString() {
		return String.format("%-20d %-35s %-15s %-20s %-50s $%12.2f \n", this.transactionNumber, this.createDate, this.customerID, this.accountNumber, this.description, this.amount);
	}
}
