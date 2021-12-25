package com.bank.CUSTACCTRACKER.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(length = 10)
	private int custId;
	private String custName;
	private String custLoc;
	private String custGender;
	private String custAccounttype;
	private Long custBalance;
}
