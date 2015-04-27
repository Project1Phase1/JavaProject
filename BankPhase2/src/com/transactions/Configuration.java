package com.transactions;

public class Configuration {
	private Integer bakup;
	private String bankName;
	
	public Configuration(Integer bakup, String bankName) {
		this.bakup = bakup;
		this.bankName = bankName;
	}
	
	// getters
	
	public Integer getBakUp() {
		return this.bakup;
	}
	
	public String getBankName() {
		return this.bankName;
	}
	
	
	// setters
	
	public void setBakUp(Integer bakup) {
		this.bakup = bakup;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
}
