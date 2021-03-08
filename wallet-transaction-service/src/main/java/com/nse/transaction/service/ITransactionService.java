package com.nse.transaction.service;

import java.util.List;

import com.nse.transaction.entity.Transaction;

public interface ITransactionService {

	
	public Transaction addTransaction(Transaction transaction);
	
	public Transaction getTransactionById(long id);
	
	public List<Transaction> getTransactionHistory(long id);
	
	public List<Long> getAccountId(long id);
	
	public List<Double> getAccountBalanceById(long id);
	
	public void updateSenderAccountBalanceById(long id , double amount);
	
	public void updateSenderTransactionBalanceById(long id , double amount);
	
	public void updateRecieverBalanceById(long id , double amount);
	
	public Long countByAccountId(long id);
}
