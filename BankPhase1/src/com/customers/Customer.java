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
		return "\t" + this.customerID + "\t"
				+ this.customerName;
	}
	
	
}
