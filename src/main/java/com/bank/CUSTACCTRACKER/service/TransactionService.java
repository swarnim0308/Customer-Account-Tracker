package com.bank.CUSTACCTRACKER.service;

import com.bank.CUSTACCTRACKER.bean.Customer;

public interface TransactionService {
	public String sendMoney(int fromId, int toId, int amount); 
}
