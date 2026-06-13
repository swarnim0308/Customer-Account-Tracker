package com.bank.custaccttracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.custaccttracker.bean.Customer;
import com.bank.custaccttracker.bean.Transactions;
import com.bank.custaccttracker.dao.CustomerDao;
import com.bank.custaccttracker.dao.TransactionDao;
import com.bank.custaccttracker.enums.TransactionStatus;

@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private TransactionDao transactionDao;

	@Override
	@Transactional
	public String sendMoney(int fromId, int toId, int amount) {
		logger.info("Transaction initiated: fromCustomerId={}, toCustomerId={}, amount={}", fromId, toId, amount);

		try {
			// Validate IDs are different
			if (fromId == toId) {
				logger.warn("Transaction failed: Same customer IDs provided - fromId={}, toId={}", fromId, toId);
				return TransactionStatus.SAME_CUSTOMER.toString();
			}

			// Fetch customers
			Customer fromCustomer = customerDao.findById(fromId).orElse(null);
			Customer toCustomer = customerDao.findById(toId).orElse(null);

			// Validate both customers exist
			if (fromCustomer == null || toCustomer == null) {
				logger.error("Transaction failed: Invalid customer IDs - fromCustomerExists={}, toCustomerExists={}",
					fromCustomer != null, toCustomer != null);
				return TransactionStatus.INVALID_CUSTOMER.toString();
			}

			// Validate sufficient balance
			if (fromCustomer.getCustBalance() < amount) {
				logger.warn("Transaction failed: Insufficient balance - customerId={}, balance={}, requestedAmount={}",
					fromId, fromCustomer.getCustBalance(), amount);
				return TransactionStatus.INSUFFICIENT_BALANCE.toString();
			}

			// Perform transaction
			fromCustomer.setCustBalance(fromCustomer.getCustBalance() - amount);
			toCustomer.setCustBalance(toCustomer.getCustBalance() + amount);

			customerDao.save(fromCustomer);
			customerDao.save(toCustomer);

			// Record transaction
			Transactions transaction = new Transactions();
			transaction.setFromCustId(fromId);
			transaction.setToCustId(toId);
			transaction.setAmount(amount);
			transactionDao.save(transaction);

			logger.info("Transaction successful: fromCustomerId={}, toCustomerId={}, amount={}, transactionId={}",
				fromId, toId, amount, transaction.getTransId());
			return TransactionStatus.SUCCESS.toString();

		} catch (Exception e) {
			logger.error("Transaction failed with exception: fromCustomerId={}, toCustomerId={}, amount={}",
				fromId, toId, amount, e);
			return TransactionStatus.FAILED.toString();
		}
	}

}
