package com.nse.account.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {

	
	private long transactionId;
	private long recieverId;
	private String description;
	public Date dateOfTransaction = new Date(System.currentTimeMillis());
	private double amount;
	private double accountBalance;
	private long accountId;
}
