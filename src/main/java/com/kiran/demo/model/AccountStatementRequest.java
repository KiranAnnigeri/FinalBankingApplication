package com.kiran.demo.model;

public class AccountStatementRequest {
	
	private String accountNumber;

	public AccountStatementRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountStatementRequest(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
}
