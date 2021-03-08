package com.nse.transaction.entity;

import java.sql.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long transactionId;
	
	
	@NotNull
	@Positive
	private Long recieverId;
	
	
	@NotBlank
	@Size(min = 5 , max = 50)
	private String description;
	public Date dateOfTransaction = new Date(System.currentTimeMillis());
	
	
	@NotNull
	@Min(100)
	@Max(10000)
	private Double amount;
	
	private double accountBalance;
	
	@NotNull
	@Positive
	private long accountId;
	
	
}
