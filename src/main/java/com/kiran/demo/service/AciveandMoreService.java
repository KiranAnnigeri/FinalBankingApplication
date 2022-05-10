package com.kiran.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NonUniqueResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiran.demo.model.Details;
import com.kiran.demo.repository.BankRepo;

@Service
public class AciveandMoreService {
	
	Logger logger = LoggerFactory.getLogger(AciveandMoreService.class);

	@Autowired
	BankRepo bankRepo;


	public List<Details> checkActivitybal() throws NonUniqueResultException { 
		logger.debug("Check whether the account is account is Active or not and Balance is more than 0 or not ");
		List<Details> list=new ArrayList<>();
		Iterable<Details> data=bankRepo.checkBal();
		for(Details filteredCustomers:data) 
		{
			list.add(filteredCustomers);
		}

		logger.info(" Account verfication Successfull");
		return list;

	}
}
