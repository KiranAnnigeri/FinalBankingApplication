package com.kiran.demo.service;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiran.demo.model.TransferBalanceRequest;
import com.kiran.demo.model.AccountStatement;
import com.kiran.demo.model.Details;
import com.kiran.demo.model.Transaction;
import com.kiran.demo.repository.AccountRepository;
import com.kiran.demo.repository.BankRepo;
import com.kiran.demo.repository.TransactionRepository;

@Service
public class AccountService {

	Logger logger = LoggerFactory.getLogger(AccountService.class);
	
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
	        logger.error("MoneyTransfer Failed");
		return null;
	}
	
	public AccountStatement getStatement(String accountNumber) {
        Details account = accountRepository.findByAccountidEquals(accountNumber);
        return new AccountStatement(account.getAccountbal(),trarepo.findByAccountNumberEquals(accountNumber));
    }

}
