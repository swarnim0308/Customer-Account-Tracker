package com.bank.custaccttracker.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(length = 10)
	@NotNull(message = "Customer ID cannot be null")
	private int custId;

	@NotBlank(message = "Customer name is required")
	@Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
	private String custName;

	@NotBlank(message = "Location is required")
	private String custLoc;

	@NotBlank(message = "Gender is required")
	private String custGender;

	@NotBlank(message = "Account type is required")
	private String custAccounttype;

	@NotNull(message = "Balance cannot be null")
	@Min(value = 0, message = "Balance cannot be negative")
	private Long custBalance;
}
