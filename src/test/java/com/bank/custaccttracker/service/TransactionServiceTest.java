package com.bank.custaccttracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.bank.custaccttracker.bean.Customer;
import com.bank.custaccttracker.dao.CustomerDao;
import com.bank.custaccttracker.enums.TransactionStatus;

@SpringBootTest
@ActiveProfiles("test")
class TransactionServiceTest {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerDao customerDao;

	private Customer sender;
	private Customer receiver;

	@BeforeEach
	void setUp() {
		// Clear existing data
		customerDao.deleteAll();

		// Create test customers
		sender = new Customer();
		sender.setCustName("John Doe");
		sender.setCustLoc("New York");
		sender.setCustGender("Male");
		sender.setCustAccounttype("Savings");
		sender.setCustBalance(1000L);
		sender = customerService.save(sender);

		receiver = new Customer();
		receiver.setCustName("Jane Smith");
		receiver.setCustLoc("Los Angeles");
		receiver.setCustGender("Female");
		receiver.setCustAccounttype("Checking");
		receiver.setCustBalance(500L);
		receiver = customerService.save(receiver);
	}

	@Test
	void testSuccessfulMoneyTransfer() {
		// Arrange
		int transferAmount = 200;
		long senderInitialBalance = sender.getCustBalance();
		long receiverInitialBalance = receiver.getCustBalance();

		// Act
		String result = transactionService.sendMoney(sender.getCustId(), receiver.getCustId(), transferAmount);

		// Assert
		assertEquals(TransactionStatus.SUCCESS.toString(), result);

		// Verify balances were updated
		Customer updatedSender = customerService.findById(sender.getCustId());
		Customer updatedReceiver = customerService.findById(receiver.getCustId());

		assertNotNull(updatedSender);
		assertNotNull(updatedReceiver);
		assertEquals(senderInitialBalance - transferAmount, updatedSender.getCustBalance());
		assertEquals(receiverInitialBalance + transferAmount, updatedReceiver.getCustBalance());
	}

	@Test
	void testTransferWithInsufficientBalance() {
		// Arrange
		int transferAmount = 2000; // More than sender's balance

		// Act
		String result = transactionService.sendMoney(sender.getCustId(), receiver.getCustId(), transferAmount);

		// Assert
		assertEquals(TransactionStatus.INSUFFICIENT_BALANCE.toString(), result);

		// Verify balances were NOT changed
		Customer unchangedSender = customerService.findById(sender.getCustId());
		Customer unchangedReceiver = customerService.findById(receiver.getCustId());

		assertNotNull(unchangedSender);
		assertNotNull(unchangedReceiver);
		assertEquals(1000L, unchangedSender.getCustBalance());
		assertEquals(500L, unchangedReceiver.getCustBalance());
	}

	@Test
	void testTransferToSameCustomer() {
		// Arrange
		int transferAmount = 100;

		// Act
		String result = transactionService.sendMoney(sender.getCustId(), sender.getCustId(), transferAmount);

		// Assert
		assertEquals(TransactionStatus.SAME_CUSTOMER.toString(), result);

		// Verify balance was NOT changed
		Customer unchangedCustomer = customerService.findById(sender.getCustId());
		assertNotNull(unchangedCustomer);
		assertEquals(1000L, unchangedCustomer.getCustBalance());
	}

	@Test
	void testTransferWithInvalidFromCustomer() {
		// Arrange
		int invalidCustomerId = 9999;
		int transferAmount = 100;

		// Act
		String result = transactionService.sendMoney(invalidCustomerId, receiver.getCustId(), transferAmount);

		// Assert
		assertEquals(TransactionStatus.INVALID_CUSTOMER.toString(), result);

		// Verify receiver balance was NOT changed
		Customer unchangedReceiver = customerService.findById(receiver.getCustId());
		assertNotNull(unchangedReceiver);
		assertEquals(500L, unchangedReceiver.getCustBalance());
	}

	@Test
	void testTransferWithInvalidToCustomer() {
		// Arrange
		int invalidCustomerId = 9999;
		int transferAmount = 100;

		// Act
		String result = transactionService.sendMoney(sender.getCustId(), invalidCustomerId, transferAmount);

		// Assert
		assertEquals(TransactionStatus.INVALID_CUSTOMER.toString(), result);

		// Verify sender balance was NOT changed
		Customer unchangedSender = customerService.findById(sender.getCustId());
		assertNotNull(unchangedSender);
		assertEquals(1000L, unchangedSender.getCustBalance());
	}


	@Test
	void testMultipleSuccessiveTransfers() {
		// Arrange
		int firstTransferAmount = 100;
		int secondTransferAmount = 50;

		// Act - First transfer
		String firstResult = transactionService.sendMoney(sender.getCustId(), receiver.getCustId(),
			firstTransferAmount);

		// Assert first transfer
		assertEquals(TransactionStatus.SUCCESS.toString(), firstResult);

		Customer afterFirstTransfer = customerService.findById(sender.getCustId());
		assertEquals(1000L - firstTransferAmount, afterFirstTransfer.getCustBalance());

		// Act - Second transfer
		String secondResult = transactionService.sendMoney(sender.getCustId(), receiver.getCustId(),
			secondTransferAmount);

		// Assert second transfer
		assertEquals(TransactionStatus.SUCCESS.toString(), secondResult);

		Customer afterSecondTransfer = customerService.findById(sender.getCustId());
		assertEquals(1000L - firstTransferAmount - secondTransferAmount, afterSecondTransfer.getCustBalance());
	}
}

