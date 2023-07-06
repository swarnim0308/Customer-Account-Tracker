package com.bank.custaccttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.custaccttracker.bean.Customer;
import com.bank.custaccttracker.bean.Transactions;
import com.bank.custaccttracker.dao.CustomerDao;
import com.bank.custaccttracker.dao.TransactionDao;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	CustomerDao dao;
	
	@Autowired
	TransactionDao daotrans;
	
	@Override
	public String sendMoney(int fromId, int toId, int amount) {
		// TODO Auto-generated method stub
		boolean c1flag =dao.findById(fromId).isPresent();
		Customer c1 = c1flag ? dao.findById(fromId).get():null;
		boolean c2flag =dao.findById(toId).isPresent();
		Customer c2 = c2flag ? dao.findById(toId).get():null;
		Transactions t=new Transactions();
		if(c1.getCustBalance()>=amount)
		{
			c1.setCustBalance(c1.getCustBalance()-amount);
			c2.setCustBalance(c2.getCustBalance()+amount);
			dao.save(c1);
			dao.save(c2);
			t.setAmount(amount);
			t.setFromCustId(fromId);
			t.setToCustId(toId);
			daotrans.save(t);
			return "success";
		}
		else
			return "fail";
	}

}
