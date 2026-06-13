package com.bank.custaccttracker.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int transId;

	@NotNull(message = "From Customer ID cannot be null")
	@Min(value = 1, message = "From Customer ID must be greater than 0")
	private int fromCustId;

	@NotNull(message = "To Customer ID cannot be null")
	@Min(value = 1, message = "To Customer ID must be greater than 0")
	private int toCustId;

	@NotNull(message = "Amount cannot be null")
	@Min(value = 1, message = "Amount must be greater than 0")
	private int amount;
}
