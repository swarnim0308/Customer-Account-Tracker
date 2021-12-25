package com.bank.CUSTACCTRACKER.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.CUSTACCTRACKER.bean.Customer;

public interface CustomerDao extends JpaRepository<Customer,Integer> {
	
}
