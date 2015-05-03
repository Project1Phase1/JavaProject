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

import java.io.Serializable;

public class Customer implements Serializable{
	private static final long serialVersionUID = 7610536679838430520L;
	private String customerID;
	private String customerName;
	private boolean active;
	
	/** customer constructor non-default
	 * 
	 * @param id
	 * @param name
	 */
	public Customer(String id, String name) {
		this.customerID = id;
		this.customerName = name;
		this.active = false;
	}
	
	// get
	
	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return this.customerID;
	}
	
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return this.customerName;
	}
	
	/**
	 * 
	 * @return whether the customer is an active customer
	 */
	public boolean getActive() {
		return this.active;
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
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public String toString() {
		return String.format("%-12s %-25s %15s", this.customerID, this.customerName, ((this.active)? "Active" : "Inactive"));
	}
	
	
}
