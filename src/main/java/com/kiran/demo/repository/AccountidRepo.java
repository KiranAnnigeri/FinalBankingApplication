package com.kiran.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiran.demo.model.Details;

@Repository
public interface AccountidRepo extends JpaRepository<Details, Long> {
	    Optional<Details> findByAccountidEquals(String accountid);
	
}









