package com.bank.CUSTACCTRACKER.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.CUSTACCTRACKER.bean.Customer;
import com.bank.CUSTACCTRACKER.dao.CustomerDao;
import com.bank.CUSTACCTRACKER.error.CustomerNotFoundException;

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
        if(!customer.isPresent()) {
            return null;
        }
        return  customer.get();
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
		List<Customer> l=new ArrayList<Customer>();
		dao.findAll().forEach(cust->l.add(cust));
		return l;
	}

	@Override
	public Customer update(Customer d) {
		return dao.save(d);
	}

}
