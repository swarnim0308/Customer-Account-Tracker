package com.bank.custaccttracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bank.custaccttracker.bean.Customer;
import com.bank.custaccttracker.bean.Transactions;
import com.bank.custaccttracker.enums.TransactionStatus;
import com.bank.custaccttracker.service.CustomerService;
import com.bank.custaccttracker.service.TransactionService;

import javax.validation.Valid;

@RequestMapping("/bank")
@Controller
public class BankController {

	private static final Logger logger = LoggerFactory.getLogger(BankController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/")
	public ModelAndView getHomePage() {
		logger.info("Accessed home page");
		return new ModelAndView("bankhome");
	}

	@RequestMapping("/customerregister")
	public ModelAndView getRegisterPage() {
		logger.info("Accessed customer registration page");
		Customer cust = new Customer();
		return new ModelAndView("registerpage", "cust", cust);
	}

	@PostMapping("/saveForm")
	public String saveForm(@ModelAttribute("cust") @Valid Customer cust,
			BindingResult result, Model m) {
		logger.info("Saving customer: {}", cust.getCustName());

		if (result.hasErrors()) {
			logger.warn("Validation errors while saving customer: {}", result.getErrorCount());
			m.addAttribute("msg2", "Validation failed. Please check all fields.");
			m.addAttribute("list", customerService.findAll());
			return "menupage";
		}

		customerService.save(cust);
		m.addAttribute("msg", "Inserted/Updated Successfully");
		m.addAttribute("list", customerService.findAll());
		return "menupage";
	}

	@GetMapping("/deleteForm")
	public ModelAndView deleteCust(@ModelAttribute("cust") Customer c) {
		logger.info("Accessed delete form for customer ID: {}", c.getCustId());
		return new ModelAndView("deletedata", "cust", c);
	}

	@PostMapping("/delete")
	public String deleteById(@ModelAttribute("cust") Customer c, Model m) {
		logger.info("Deleting customer ID: {}", c.getCustId());

		String result = customerService.deleteById(c.getCustId());
		if ("success".equals(result)) {
			m.addAttribute("msg", "Deleted Successfully");
		} else {
			m.addAttribute("msg2", "Invalid Id");
		}
		m.addAttribute("list", customerService.findAll());
		return "menupage";
	}

	@GetMapping("/makeTransac")
	public ModelAndView makeTransac(@ModelAttribute("cust") Customer c) {
		logger.info("Accessed transaction page for customer ID: {}", c.getCustId());
		Transactions t = new Transactions();
		ModelAndView mv = new ModelAndView("maketransaction", "trans", t);
		mv.addObject("cust", c);
		return mv;
	}

	@RequestMapping("/maketrans")
	public String makeTransaction(@ModelAttribute("trans") @Valid Transactions t,
			BindingResult transResult,
			@ModelAttribute("cust") Customer c, Model m) {
		logger.info("Processing transaction from customer {} to {}", t.getFromCustId(), t.getToCustId());

		if (transResult.hasErrors()) {
			logger.warn("Transaction validation failed: {}", transResult.getErrorCount());
			m.addAttribute("msg2", "Invalid transaction data. Please check all fields.");
			m.addAttribute("list", customerService.findAll());
			return "menupage";
		}

		String transactionResult = transactionService.sendMoney(t.getFromCustId(), t.getToCustId(), t.getAmount());

		if (TransactionStatus.SUCCESS.toString().equals(transactionResult)) {
			m.addAttribute("msg", "Money Sent successfully");
		} else if (TransactionStatus.INSUFFICIENT_BALANCE.toString().equals(transactionResult)) {
			m.addAttribute("msg2", "Insufficient balance");
		} else if (TransactionStatus.INVALID_CUSTOMER.toString().equals(transactionResult)) {
			m.addAttribute("msg2", "Invalid customer ID's entered");
		} else if (TransactionStatus.SAME_CUSTOMER.toString().equals(transactionResult)) {
			m.addAttribute("msg2", "Cannot transfer to the same customer");
		} else {
			m.addAttribute("msg2", "Transaction failed");
		}

		m.addAttribute("list", customerService.findAll());
		return "menupage";
	}

	@GetMapping("/updateForm")
	public ModelAndView updateCust(@ModelAttribute("cust") Customer c) {
		logger.info("Accessed update form for customer ID: {}", c.getCustId());
		return new ModelAndView("updatepage", "cust", c);
	}

	@PostMapping("/update")
	public String updatebyId(@ModelAttribute("cust") Customer c, Model m) {
		logger.info("Fetching customer for update: ID={}", c.getCustId());

		Customer existingCustomer = customerService.findById(c.getCustId());
		if (existingCustomer != null) {
			m.addAttribute("cust", existingCustomer);
			return "updatedetails";
		} else {
			logger.warn("Customer not found for update: ID={}", c.getCustId());
			m.addAttribute("msg2", "Invalid Cust Id");
			m.addAttribute("list", customerService.findAll());
			return "menupage";
		}
	}

	@PostMapping("/updateCustData")
	public String updateDetails(@ModelAttribute("cust") @Valid Customer c,
			BindingResult result, Model m) {
		logger.info("Updating customer details: ID={}", c.getCustId());

		if (result.hasErrors()) {
			logger.warn("Validation errors while updating customer: {}", result.getErrorCount());
			m.addAttribute("msg2", "Validation failed. Please check all fields.");
			m.addAttribute("cust", c);
			return "updatedetails";
		}

		customerService.update(c);
		m.addAttribute("msg", "Customer updated successfully");
		m.addAttribute("list", customerService.findAll());
		return "menupage";
	}

	@GetMapping("/retrieveForm")
	public ModelAndView retrieveCust(@ModelAttribute("cust") Customer c) {
		logger.info("Accessed retrieve form for customer ID: {}", c.getCustId());
		return new ModelAndView("retrievedata", "cust", c);
	}

	@GetMapping("/getMenuPage")
	public ModelAndView getMenuPage(@ModelAttribute("cust") Customer c) {
		logger.info("Accessing menu page");
		return new ModelAndView("menupage", "cust", c);
	}

	@PostMapping("/retrieve")
	public String retrieveById(@ModelAttribute("cust") Customer c, Model m) {
		logger.info("Retrieving customer: ID={}", c.getCustId());

		Customer cust = customerService.findById(c.getCustId());
		if (cust != null) {
			m.addAttribute("cust", cust);
		} else {
			logger.warn("Customer not found: ID={}", c.getCustId());
			m.addAttribute("msg2", "Customer not found");
		}
		return "retrievedData";
	}
}
