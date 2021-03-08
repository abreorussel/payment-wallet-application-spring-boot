package com.nse.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.nse.account.entity.Account;
import com.nse.account.repository.AccountRepository;
import com.nse.account.vo.Transaction;



@Service
public class AccountService implements IAccountService{

	@Autowired
	AccountRepository repo;
	
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	
	@Override
	public Account addAccount(Account account) {
		
		return repo.save(account);
	}

	@Override
	public Account getBalanceById(long id) {
		
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<Transaction> getTransactionHistory(long id) {
		
		
		List<Transaction> transactions = webClientBuilder.build()
				.get()  
				.uri("http://TRANSACTION-SERVICE/api/transaction-history/"+id)   
				.retrieve()
				.bodyToMono(List.class)       
				.block();
		
		
		return transactions;
	}

	@Override
	public Account getAccountById(long id) {
		
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<Integer> getNumberofRows(long id) {
		
		return repo.getNumberofRows(id);
	}

	@Override
	public void updateAccountId(long accountId, long userId) {
		repo.updateAccountId(accountId, userId);
		
	}

	@Override
	public List<Long> getUserId(long id) {
		
		return repo.getUserId(id);
	}

	@Override
	public void updateAccountBalanceById(long id, double balance) {
		repo.updateAccountBalanceById(id, balance);
		
	}

	@Override
	public Account findByUserId(long id) {
		
		return repo.findByUserId(id);
	}

	@Override
	public List<Long> getAccountIdFromUser(long id) {
		
		return repo.getAccountIdFromUser(id);
	}



	
}
