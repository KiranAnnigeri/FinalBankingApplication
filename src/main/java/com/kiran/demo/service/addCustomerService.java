package com.kiran.demo.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kiran.demo.model.Details;
import com.kiran.demo.repository.AccountRepository;
import com.kiran.demo.repository.AccountidRepo;
import com.kiran.demo.repository.BankRepo;
import com.kiran.demo.repository.TransactionRepository;

@Service
public class addCustomerService {
	
	Logger logger = LoggerFactory.getLogger(addCustomerService.class);

	@Autowired
	BankRepo bankrepo;
	@Autowired
	List<Details> details;
	@Autowired
	AccountRepository accrepo;
	@Autowired
	AccountidRepo accidrepo;

	//get all data
	public List<Details> getAllOrders() {
		logger.debug(" Get all Customer Details");
		logger.info(" Successfully got all customer Details ");
		return (List<Details>) bankrepo.findAll();
		
	}
	
	
	//Input the data
	public Details addCustomer (Details details) throws Exception {
		Optional<Details> AccountId = accidrepo.findByAccountidEquals(details.getAccountid());
		if(AccountId.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account number already exists!!");
		}
		else {
		return bankrepo.save(details);
		}
	}
	
	//Get data by Customer ID
	public Details getById(String id) throws Exception {
		Optional<Details> AccountId = accidrepo.findByAccountidEquals(id);
		if(AccountId.isPresent()) {
		return accrepo.findByAccountidEquals(id);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account number does not exists!!");
		}
	}
	

}
