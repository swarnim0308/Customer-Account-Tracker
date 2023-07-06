package com.bank.custaccttracker.service;

public interface TransactionService {
	String sendMoney(int fromId, int toId, int amount);
}
