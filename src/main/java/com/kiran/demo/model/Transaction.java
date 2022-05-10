package com.kiran.demo.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "transaction")
public class Transaction {

	 public Transaction(String fromAccountNumber,String toAccountNumber, BigDecimal amount, Timestamp timestamp) {
		super();
		//this.transactionId = transactionId;
		this.accountNumber = fromAccountNumber;
		this.transactionAmount = amount;
		this.accountNumber1=toAccountNumber; 
		this.transactionDateTime = timestamp;
	}

	public Transaction() {
		super();
	}


		@Id
	    @GeneratedValue
	    private Long transactionId;

		@Column(name="From_AccountNumber")
	    private String accountNumber;

		@Column(name="To_AccountNumber")
	    private String accountNumber1;
		
	    private BigDecimal transactionAmount;

	    private Timestamp transactionDateTime;

		public Long getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(Long transactionId) {
			this.transactionId = transactionId;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public BigDecimal getTransactionAmount() {
			return transactionAmount;
		}

		public void setTransactionAmount(BigDecimal transactionAmount) {
			this.transactionAmount = transactionAmount;
		}

		public Timestamp getTransactionDateTime() {
			return transactionDateTime;
		}

		public void setTransactionDateTime(Timestamp transactionDateTime) {
			this.transactionDateTime = transactionDateTime;
		}

		@Override
		public String toString() {
			return "Transaction [transactionId=" + transactionId + ", accountNumber=" + accountNumber
					+ ", transactionAmount=" + transactionAmount + ", transactionDateTime=" + transactionDateTime + "]";
		}

}
