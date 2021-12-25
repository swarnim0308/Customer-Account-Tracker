package com.bank.CUSTACCTRACKER.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.CUSTACCTRACKER.bean.Customer;
import com.bank.CUSTACCTRACKER.bean.Transactions;
import com.bank.CUSTACCTRACKER.dao.CustomerDao;
import com.bank.CUSTACCTRACKER.dao.TransactionDao;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	CustomerDao dao;
	
	@Autowired
	TransactionDao daotrans;
	
	@Override
	public String sendMoney(int fromId, int toId, int amount) {
		// TODO Auto-generated method stub
		Customer c1=dao.findById(fromId).get();
		Customer c2=dao.findById(toId).get();
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
