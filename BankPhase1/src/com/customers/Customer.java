package com.customers;

public class Customer {
	private String customerID;
	private String customerName;
	
	/** customer constructor non-default
	 * 
	 * @param id
	 * @param name
	 */
	public Customer(String id, String name) {
		this.customerID = id;
		this.customerName = name;
	}
	
	// get
	
	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}
	
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	// set
	
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String toString() {
		return "[customerID=" + this.customerID + ", customerName="
				+ this.customerName + "]";
	}
	
	
}
