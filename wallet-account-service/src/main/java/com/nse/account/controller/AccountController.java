package com.nse.account.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nse.account.entity.Account;
import com.nse.account.enumeration.Status;
import com.nse.account.exceptions.AccountAlreadyExistsException;
import com.nse.account.exceptions.AccountIdNotFoundException;
import com.nse.account.exceptions.EmptyTableException;
import com.nse.account.exceptions.MinimumBalanceException;
import com.nse.account.exceptions.TransactionNotFoundException;
import com.nse.account.exceptions.UserNotFoundException;
import com.nse.account.service.IAccountService;

import com.nse.account.vo.Transaction;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	IAccountService service;

	@PostMapping("/account")
	public Account addAccount(@Valid @RequestBody Account account)
			throws MinimumBalanceException, UserNotFoundException, AccountAlreadyExistsException {

		if (account.getAccountBalance() < IConstants.MINIMUM_BALANCE) {
			throw new MinimumBalanceException(
					"Could not create account. Minimum balance required is Rs." + IConstants.MINIMUM_BALANCE);
		}
		if (service.getUserId(account.getUserId()).isEmpty()) {
			throw new UserNotFoundException("User ID does not exist.");
		} 
		if(service.getAccountIdFromUser(account.getUserId()).get(0) != 0) {
			throw new AccountAlreadyExistsException("Account already exists.");
		}
		else {
			account.setStatus(Status.ACTIVE);

			Account addAccount = service.addAccount(account);

			service.updateAccountId(addAccount.getAccountId(), addAccount.getUserId());

			return account;
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateAccount(@Valid @PathVariable long id, @RequestBody Account account)
			throws AccountIdNotFoundException {

		Account accountRef = service.getAccountById(id);

		if (accountRef == null) {
			throw new AccountIdNotFoundException("The given Account ID does not exist.");
		} else {

			Double updatedBalance = account.getAccountBalance() + service.getBalanceById(id).getAccountBalance();

			service.updateAccountBalanceById(id, updatedBalance);

			return new ResponseEntity<String>("Balance is successfully updated.", HttpStatus.OK);
		}
	}

	@GetMapping("/account-balance/{id}")
	public ResponseEntity<String> getBalanceById(@PathVariable long id) throws AccountIdNotFoundException {

		Account account = service.getAccountById(id);

		if (account == null) {
			throw new AccountIdNotFoundException("The given Account ID does not exist.");
		} else {
			return new ResponseEntity<String>("Your balance is Rs." + service.getBalanceById(id).getAccountBalance(),
					HttpStatus.OK);
		}

	}

	@GetMapping("/alltransactions/{id}")
	public List<Transaction> getTransactionHistory(@PathVariable long id)
			throws AccountIdNotFoundException, TransactionNotFoundException, EmptyTableException {

		Account account = service.getAccountById(id);

		if (account == null) {
			throw new AccountIdNotFoundException("The given Account ID does not exist.");
		}
		if (service.getNumberofRows(id).get(0) == 0) {
			throw new TransactionNotFoundException("No transactions present.");
		} else {

			return service.getTransactionHistory(id);

		}

	}

	@GetMapping("/account/{id}")
	public Account getAccountById(@PathVariable long id) throws AccountIdNotFoundException {

		Account account = service.getAccountById(id);

		if (account == null) {
			throw new AccountIdNotFoundException("Account ID does not exist.");
		} else {
			return account;
		}
	}

	@GetMapping("/user-id/{id}")
	public Account getUserId(@Valid @PathVariable long id) throws AccountIdNotFoundException {

		Account account = service.findByUserId(id);

		if (account == null) {
			throw new AccountIdNotFoundException("Account for this user doesn't exist.");
		} else {
			return account;
		}
	}

}
