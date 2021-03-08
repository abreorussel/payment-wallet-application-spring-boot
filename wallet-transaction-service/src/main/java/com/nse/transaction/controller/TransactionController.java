package com.nse.transaction.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.DoubleNode;
import com.nse.transaction.entity.Transaction;
import com.nse.transaction.exceptions.AccountIdNotFoundException;
import com.nse.transaction.exceptions.EmptyTableException;
import com.nse.transaction.exceptions.InvalidAccountIdException;
import com.nse.transaction.exceptions.InvalidTransactionException;
import com.nse.transaction.exceptions.MinimumBalanceException;
import com.nse.transaction.exceptions.TransactionIdNotFoundException;
import com.nse.transaction.exceptions.TransactionNotFoundException;
import com.nse.transaction.service.ITransactionService;


@RestController
@RequestMapping("/api")
public class TransactionController {

	@Autowired
	ITransactionService service;
	
	

	@PostMapping("/transaction")
	public Transaction addTransaction(@Valid @RequestBody Transaction transaction) throws AccountIdNotFoundException,
			MinimumBalanceException, InvalidTransactionException, InvalidAccountIdException {
		
		if (service.getAccountId(transaction.getAccountId()).isEmpty()) {

			throw new AccountIdNotFoundException("Account ID does not exist.");
		}
		if (service.getAccountId(transaction.getRecieverId()).isEmpty()) {

			throw new AccountIdNotFoundException("Reciever ID does not exist.");
		}
		if (transaction.getAccountId() == transaction.getRecieverId()) {
			throw new InvalidTransactionException("Check the account ID .Cannot transact to the same account.");
		} else {
			Double senderBalance = service.getAccountBalanceById(transaction.getAccountId()).get(0);
			Double recieverBalance = service.getAccountBalanceById(transaction.getRecieverId()).get(0);

			Double newSenderBalance = senderBalance - transaction.getAmount();
			Double newRecieverBalance = recieverBalance + transaction.getAmount();

			if (newSenderBalance < IConstants.MINIMUM_BALANCE) {
				throw new MinimumBalanceException(
						"Transaction cannot occur. Minimum balance in the account required is Rs."
								+ IConstants.MINIMUM_BALANCE);
			} else {

				Transaction addTransaction = service.addTransaction(transaction);

				service.updateSenderAccountBalanceById(transaction.getAccountId(), newSenderBalance);
				service.updateSenderTransactionBalanceById(addTransaction.getTransactionId(), newSenderBalance);
				service.updateRecieverBalanceById(transaction.getRecieverId(), newRecieverBalance);

				return service.addTransaction(addTransaction);
			}

		}

	}

	@GetMapping("/transaction/{id}")
	public Transaction getTransactionById(@PathVariable long id) throws TransactionIdNotFoundException {

		Transaction transaction = service.getTransactionById(id);

		if (transaction == null) {
			throw new TransactionIdNotFoundException("Transaction does not exist");
		} else {
			return transaction;
		}
	}

	@GetMapping("/transaction-history/{id}")
	public List<Transaction> getTransactionHistory(@Valid @PathVariable long id)
			throws TransactionNotFoundException, EmptyTableException, AccountIdNotFoundException {

		if(service.getAccountId(id).isEmpty()) {
			throw new AccountIdNotFoundException("Account ID does not exist.");
		}
		if (service.countByAccountId(id) == 0) {
			throw new TransactionNotFoundException("No transactions present.");
		} else {

			return service.getTransactionHistory(id);

		}

	}

}
