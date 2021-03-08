package com.nse.account.service;

import java.util.List;

import com.nse.account.entity.Account;
import com.nse.account.vo.Transaction;

public interface IAccountService {

	public Account addAccount(Account account);
	
	public Account getBalanceById(long id);
	
	public List<Transaction> getTransactionHistory(long id);
	
	public Account getAccountById(long id);
	
	public List<Integer> getNumberofRows(long id);
	
	public void updateAccountId(long accountId , long userId);
	
	public List<Long> getUserId(long id);
	
	public void updateAccountBalanceById(long id , double balance);
	
	public Account findByUserId(long id);
	
	public List<Long> getAccountIdFromUser(long id);
	
}
