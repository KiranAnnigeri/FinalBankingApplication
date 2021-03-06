package com.kiran.demo.service;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kiran.demo.model.TransferBalanceRequest;
import com.kiran.demo.model.AccountStatement;
import com.kiran.demo.model.Details;
import com.kiran.demo.model.Transaction;
import com.kiran.demo.repository.AccountRepository;
import com.kiran.demo.repository.BankRepo;
import com.kiran.demo.repository.TransactionRepository;

@Service
public class AccountService {

	@Autowired
	Details detail;
	
	@Autowired
	BankRepo bankrepo;
	
	@Autowired
	TransactionRepository trarepo;
	
	@Autowired
	AccountRepository accountRepository;
	
	public Transaction sendMoney(TransferBalanceRequest transferBalanceRequest) {
		String fromAccountNumber = transferBalanceRequest.getFromAccountNumber();
		String toAccountNumber = transferBalanceRequest.getToAccountNumber();
		BigDecimal amount = transferBalanceRequest.getAmount();
		 Details fromAccount = accountRepository.findByAccountidEquals(fromAccountNumber);
		 Details toAccount = accountRepository.findByAccountidEquals(toAccountNumber);
		 if(fromAccount.isAccountstatus() && toAccount.isAccountstatus()) {
	        if(fromAccount.getAccountbal().compareTo(BigDecimal.ONE) == 1
	                && fromAccount.getAccountbal().compareTo(amount) == 1){
	            fromAccount.setAccountbal(fromAccount.getAccountbal().subtract(amount));
	            accountRepository.save(fromAccount);
	            toAccount.setAccountbal(toAccount.getAccountbal().add(amount));
	            accountRepository.save(toAccount);
	            Transaction transaction = new Transaction(fromAccountNumber,toAccountNumber,amount,new Timestamp(System.currentTimeMillis()));
	            Transaction transaction1 = trarepo.save(transaction);
	            return transaction1;
	        }
	        else {
	        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						" Insufficient Balance in Account number : " +fromAccountNumber + ". Available balance is " +fromAccount.getAccountbal());
	        }
		 }
		 else {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Acount should be active" );
		 }
	}
	
	public AccountStatement getStatement(String accountNumber) {
        Details account = accountRepository.findByAccountidEquals(accountNumber);
        return new AccountStatement(account.getAccountbal(),trarepo.findByAccountNumberEquals(accountNumber));
    }

}
