package com.bank.custaccttracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.custaccttracker.bean.Customer;
import com.bank.custaccttracker.dao.CustomerDao;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired  
	CustomerDao dao;
	
	@Override
	public Customer save(Customer d) {
		return dao.save(d);
	}

	@Override
	public Customer findById(Integer id) {
        Optional<Customer> customer = dao.findById(id);
		return customer.orElse(null);
	}

	@Override
	public String deleteById(Integer id) {
		Optional<Customer> customer = dao.findById(id);
        if(customer.isPresent()) {
            dao.deleteById(id);
        	return "success";
        }
        else
        	return "";
	}

	@Override
	public List<Customer> findAll() {
		ArrayList<Customer> l;
		l = new ArrayList<>(dao.findAll());
		return l;
	}

	@Override
	public Customer update(Customer d) {
		return dao.save(d);
	}

}
