package com.bank.custaccttracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.custaccttracker.bean.Transactions;

public interface TransactionDao extends JpaRepository<Transactions,Integer> {

}
