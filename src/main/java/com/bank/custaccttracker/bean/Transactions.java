package com.bank.custaccttracker.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int transId;
	private int fromCustId;
	private int toCustId;
	private int amount;
}
