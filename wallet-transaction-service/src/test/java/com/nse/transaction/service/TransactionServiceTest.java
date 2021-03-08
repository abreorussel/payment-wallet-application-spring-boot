package com.nse.transaction.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nse.transaction.entity.Transaction;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TransactionServiceTest {

	@Autowired
	ITransactionService service;

	@Autowired
	Transaction transaction;

	@Test
	void testAddTransaction() {

		transaction.setAccountId(89L);
		transaction.setAmount(100.0);
		transaction.setDescription("medical purpose");
		transaction.setRecieverId(88L);

		Transaction transaction2 = service.addTransaction(transaction);

		Assertions.assertEquals(transaction2.toString(), transaction.toString());

	}

	@Test
	void testGetTransactionById() {

		transaction.setTransactionId(90L);

		Assertions.assertEquals(transaction.getTransactionId(), service.getTransactionById(transaction.getTransactionId()).getTransactionId());
	}

	@Test
	void testGetTransactionHistory() {
		
		transaction.setTransactionId(90L);
		transaction.setAccountBalance(5000.0);
		transaction.setAccountId(88L);
		transaction.setAmount(1000.0);
		transaction.setDateOfTransaction(Date.valueOf("2021-03-04"));
		transaction.setDescription("shopping");
		transaction.setRecieverId(89L);
		
		Transaction transaction2 = new Transaction(91L, 89L, "shopping", Date.valueOf("2021-03-04"), 500.0 , 4500.0 ,88L);
		
		List<Transaction> list = new ArrayList<>();
		list.add(transaction);
		list.add(transaction2);
		

		Assertions.assertEquals(list.toString(), service.getTransactionHistory(transaction.getAccountId()).toString());
		
	}

	@Test
	void testGetAccountId() {

		List<Long> accountId = new ArrayList<Long>();
		accountId.add(89L);

		Assertions.assertEquals(accountId.get(0), service.getAccountId(accountId.get(0)).get(0));
	}

	

	@Test
	void testCountByAccountId() {
		
		transaction.setAccountId(99L);
		
		Assertions.assertEquals(0, service.countByAccountId(transaction.getAccountId()));
	}

}
