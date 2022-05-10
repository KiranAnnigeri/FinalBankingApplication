package com.kiran.demo.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiran.demo.model.Details;
import com.kiran.demo.repository.BankRepo;

@Service
public class addCustomerService {
	
	Logger logger = LoggerFactory.getLogger(addCustomerService.class);

	@Autowired
	BankRepo bankrepo;
	@Autowired
	List<Details> details;

	//get all data
	public List<Details> getAllOrders() {
		logger.debug(" Get all Customer Details");
		logger.info(" Successfully got all customer Details ");
		return (List<Details>) bankrepo.findAll();
		
	}
	
	
	//Input the data
	public Details addCustomer (Details details) {
		logger.debug(" Add Customer Details");
		logger.info(" Successfully added new customer Details ");
		return bankrepo.save(details);
	}
	
	//Get data by Customer ID
	public Details getById(int id) {
		Optional<Details> optional = bankrepo.findById(id);
		Details detail = null;
		if (optional.isPresent()) {
			detail = optional.get();
		} else {
			throw new RuntimeException(" Customer not found for id :: " + id);
		}
		logger.debug(" Successfully Getting Customer Details By Id ");
		return detail;
	}
	

}
