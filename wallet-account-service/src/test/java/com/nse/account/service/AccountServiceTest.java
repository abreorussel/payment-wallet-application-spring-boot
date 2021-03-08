package com.nse.account.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nse.account.entity.Account;
import com.nse.account.enumeration.Status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccountServiceTest {

	@Autowired
	IAccountService service;

	@Autowired
	Account account;
	
	@Test
	void testAddAccount() {
		
		account.setAccountBalance(3500.0);
		account.setUserId(85L);
		account.setStatus(Status.ACTIVE);
		
		Account account1 = service.addAccount(account);
		
		Assertions.assertEquals(account.toString(), account1.toString());
		
	}

	@Test
	void testGetBalanceById() {
		
		account.setAccountId(88L);
		account.setAccountBalance(4500.0);
		
		Assertions.assertEquals(account.getAccountBalance(), service.getBalanceById(account.getAccountId()).getAccountBalance());
	}

	

	@Test
	void testGetAccountById() {
		
		account.setAccountId(88L);
		account.setAccountBalance(4500.0);
		account.setStatus(Status.ACTIVE);
		account.setUserId(87L);
		
		Assertions.assertEquals(account.toString(), service.getAccountById(account.getAccountId()).toString());
		
	}

	

	@Test
	void testGetUserId() {
		account.setUserId(88L);
		
		Assertions.assertEquals(true, service.getUserId(account.getUserId()).isEmpty());
	}

	@Test
	void testUpdateAccountBalanceById() {
		
		account.setAccountId(89L);
		account.setAccountBalance(4000.0);
		
		service.updateAccountBalanceById(account.getAccountId(), account.getAccountBalance());
		
		Assertions.assertEquals(account.getAccountBalance(), service.getBalanceById(account.getAccountId()).getAccountBalance());
		
		
	}

	@Test
	void testFindByUserId() {
		account.setAccountId(88L);
		account.setAccountBalance(4500.0);
		account.setStatus(Status.ACTIVE);
		account.setUserId(87L);
		
		Assertions.assertEquals(account.toString(), service.findByUserId(account.getUserId()).toString());
	}

}
