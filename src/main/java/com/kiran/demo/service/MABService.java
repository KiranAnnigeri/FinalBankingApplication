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
public class MABService {
	
	Logger logger = LoggerFactory.getLogger(MABService.class);

	@Autowired
	BankRepo bankRepo;


	public List<Details> checkActivitymab() throws NonUniqueResultException { 
		logger.debug(" Check Whether the MAB is Above 1000 or not ");
		List<Details> list=new ArrayList<>();
		Iterable<Details> details=bankRepo.checkMAB();
		for(Details filteredCustomers:details) 
		{
			list.add(filteredCustomers);
		}

		logger.info("Successfully Verified The MAB ");
		return list;

	}
}
