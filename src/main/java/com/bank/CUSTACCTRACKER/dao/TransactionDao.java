package com.bank.CUSTACCTRACKER.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.CUSTACCTRACKER.bean.Transactions;

public interface TransactionDao extends JpaRepository<Transactions,Integer> {

}
