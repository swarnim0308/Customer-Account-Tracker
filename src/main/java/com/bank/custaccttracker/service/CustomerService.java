package com.bank.custaccttracker.service;

import java.util.List;

import com.bank.custaccttracker.bean.Customer;

public interface CustomerService {
	Customer save(Customer d);
	Customer findById(Integer id);
	Customer update(Customer d);
	String deleteById(Integer id);
	List<Customer> findAll();
}
