package com.nse.transaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nse.transaction.entity.Transaction;
import com.nse.transaction.repository.TransactionRepository;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	TransactionRepository repo;
	
	
	@Override
	public Transaction addTransaction(Transaction transaction) {
		
		return repo.save(transaction);
	}

	@Override
	public Transaction getTransactionById(long id) {
		
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<Transaction> getTransactionHistory(long id) {
		
		return repo.getTransactionHistory(id);
	}

	@Override
	public List<Long> getAccountId(long id) {
		
		return repo.getAccountId(id);
	}

	@Override
	public List<Double> getAccountBalanceById(long id) {
		
		return repo.getAccountBalanceById(id);
	}

	@Override
	public void updateSenderAccountBalanceById(long id, double amount) {
		
		repo.updateSenderAccountBalanceById(id, amount);
	}

	@Override
	public void updateSenderTransactionBalanceById(long id, double amount) {
		
		repo.updateSenderTransactionBalanceById(id, amount);
	}

	@Override
	public void updateRecieverBalanceById(long id, double amount) {
		
		repo.updateRecieverBalanceById(id, amount);
	}

	@Override
	public Long countByAccountId(long id) {
		
		return repo.countByAccountId(id);
	}

}
