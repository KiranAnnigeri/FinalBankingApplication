package com.kiran.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiran.demo.model.Details;

@Repository
public interface AccountRepository extends JpaRepository<Details, Long> {
    Details findByAccountidEquals(String accountid);

}