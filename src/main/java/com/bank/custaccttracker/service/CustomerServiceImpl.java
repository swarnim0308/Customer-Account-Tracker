package com.bank.custaccttracker.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.custaccttracker.bean.Customer;
import com.bank.custaccttracker.dao.CustomerDao;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDao customerDao;

	@Override
	public Customer save(Customer customer) {
		logger.info("Saving/updating customer: custName={}, custId={}", customer.getCustName(), customer.getCustId());
		return customerDao.save(customer);
	}

	@Override
	public Customer findById(Integer id) {
		logger.debug("Fetching customer with ID: {}", id);
		Optional<Customer> customer = customerDao.findById(id);
		return customer.orElse(null);
	}

	@Override
	public String deleteById(Integer id) {
		logger.info("Attempting to delete customer with ID: {}", id);
		Optional<Customer> customer = customerDao.findById(id);
		if (customer.isPresent()) {
			customerDao.deleteById(id);
			logger.info("Customer deleted successfully: custId={}", id);
			return "success";
		}
		logger.warn("Cannot delete customer - ID not found: custId={}", id);
		return "";
	}

	@Override
	public List<Customer> findAll() {
		logger.debug("Fetching all customers");
		return customerDao.findAll();
	}

	@Override
	public Customer update(Customer customer) {
		logger.info("Updating customer: custName={}, custId={}", customer.getCustName(), customer.getCustId());
		return customerDao.save(customer);
	}

}
