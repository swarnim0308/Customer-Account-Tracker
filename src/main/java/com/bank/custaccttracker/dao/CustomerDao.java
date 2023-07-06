package com.bank.custaccttracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.custaccttracker.bean.Customer;

public interface CustomerDao extends JpaRepository<Customer,Integer> {
	
}
