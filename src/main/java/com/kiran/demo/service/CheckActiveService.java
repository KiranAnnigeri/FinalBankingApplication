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
public class CheckActiveService {
	
	Logger logger = LoggerFactory.getLogger(CheckActiveService.class);

	@Autowired
	BankRepo bankRepo;
	
	public List<Details> checkActivity(){ 
		List<Details> list=new ArrayList<>();
		Iterable<Details> details=bankRepo.isActiveForThreeYears();
		for(Details filteredCustomers:details) 
		{
			list.add(filteredCustomers);
		}

		logger.debug(" Verified That Account is Active or Not ");
		return list;

	}
}
