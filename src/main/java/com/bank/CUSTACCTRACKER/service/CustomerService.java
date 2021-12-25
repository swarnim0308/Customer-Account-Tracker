package com.bank.CUSTACCTRACKER.service;

import java.util.List;

import com.bank.CUSTACCTRACKER.bean.Customer;
import com.bank.CUSTACCTRACKER.error.CustomerNotFoundException;

public interface CustomerService {
	public Customer save(Customer d);   
	public Customer findById(Integer id);   
	public Customer update(Customer d);   
	public String deleteById(Integer id);  
	public List<Customer> findAll();
}
