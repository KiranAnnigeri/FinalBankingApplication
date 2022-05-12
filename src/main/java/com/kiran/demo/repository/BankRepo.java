package com.kiran.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kiran.demo.model.Details;

public interface BankRepo extends CrudRepository<Details, Integer> {
	
	 // Details findByAccountIdEquals(String accountId);

	  @Query("from Details where Account_Status='true'") 
	  public Iterable<Details> isActiveForThreeYears();
	  
	  @Query("from Details where Account_Bal>0 AND Account_Status='true'") 
	  public Iterable<Details> checkBal();
	
	  @Query("from Details where MAB>=1000 AND Account_Bal>0 AND Account_Status='true'") 
	  public Iterable<Details> checkMAB();
	  

}





