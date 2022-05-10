package com.kiran.demo.model;

import java.math.BigDecimal;
import java.util.List;

public class AccountStatement {
	BigDecimal currentBalance;
	List<Transaction> transactionHistory;

	public AccountStatement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountStatement(BigDecimal currentBalance, List<Transaction> transactionHistory) {
		super();
		this.currentBalance = currentBalance;
		this.transactionHistory = transactionHistory;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public List<Transaction> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(List<Transaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

	
}