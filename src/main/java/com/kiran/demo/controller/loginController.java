package com.kiran.demo.controller;

import java.util.List;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kiran.demo.model.AccountStatement;
import com.kiran.demo.model.AccountStatementRequest;
import com.kiran.demo.model.Details;
import com.kiran.demo.model.Transaction;
import com.kiran.demo.model.TransferBalanceRequest;
import com.kiran.demo.service.AccountService;
import com.kiran.demo.service.AciveandMoreService;
import com.kiran.demo.service.CheckActiveService;
import com.kiran.demo.service.MABService;
import com.kiran.demo.service.addCustomerService;

@RestController
public class loginController {
	
	Logger logger = LoggerFactory.getLogger(loginController.class);

	@Autowired
	private CheckActiveService checkactiveService;
	@Autowired
	private AciveandMoreService activeandmoreService;
	@Autowired
	private MABService mabService;
	@Autowired
	private addCustomerService addcustomerService;
	@Autowired
	private AccountService accountservice;

	//Get Details of all the active accounts
	@RequestMapping("/ActiveAccounts")
	public List<Details> checkActive() {
		logger.info("Checking the Active Status");
		return checkactiveService.checkActivity();
	}

	//Get Details of all active accounts and positive balance
	@RequestMapping("/ActiveAccounts/Balance>0")
	public List<Details> checkActiveBal() {
		logger.info(" Verifing the validation ");
		logger.trace(" validation is tracing");
		return activeandmoreService.checkActivitybal();
	}

	//Get Details of all active account , positive balance and MAB>1000
	@RequestMapping("/ActiveAccounts/Balance>0/MonthlyAverageBalance>1000")
	public List<Details> checkActiveBalMab() {
		logger.trace(" Checking Balance");
		logger.info("giving monthly average balance");
		return mabService.checkActivitymab();
	}

	//Get Details of all accounts
	@RequestMapping("/GetAllAccounts")
	public List<Details> getalldetails() {
		logger.debug(" Provide All customer details ");
		return addcustomerService.getAllOrders();
	}

	// Input Customer Details
	@PostMapping(value = "/AddCustomerDetails")
	public void addCustomer(@Valid @RequestBody Details details) {
		logger.debug("Successfully Posted all Details of Customer ");
		addcustomerService.addCustomer(details);
	}

	// Find customer by ID
	@GetMapping(value = "/GetDetailsByID/{id}")
	public Details getbyID(@PathVariable(value = "id") String id) {
		logger.debug("Get Cutomer details By Account Id ");
		return addcustomerService.getById(id);
	}

	// TransferMoney From one account to another
	@PostMapping(value = "/TransferMoney")
	public Transaction sendMoney(@RequestBody TransferBalanceRequest transferBalanceRequest) {
		logger.debug(" Transfering of Money is Successfull");
		return accountservice.sendMoney(transferBalanceRequest);
	}

	// Get statement
	@RequestMapping("/GetStatement")
	public AccountStatement getStatement(@RequestBody AccountStatementRequest accountStatementRequest){
		logger.debug(" Providing the Account Statement ");
	     return accountservice.getStatement(accountStatementRequest.getAccountNumber());
	    }
}
