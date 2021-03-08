package com.nse.user.controller;

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

import com.nse.user.entity.User;
import com.nse.user.exceptions.AccountNotCreatedException;
import com.nse.user.exceptions.EmailAlreadyExistsException;

import com.nse.user.exceptions.NameAlreadyExistsException;
import com.nse.user.exceptions.PasswordAlreadyExistsException;
import com.nse.user.exceptions.PhoneNumberAlreadyExistsException;
import com.nse.user.exceptions.UserNotFoundException;
import com.nse.user.service.IUserService;
import com.nse.user.vo.ResponseTemplateVO;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	IUserService service;

	@PostMapping("/user")
	public User addUser(@Valid @RequestBody User user) throws NameAlreadyExistsException, PhoneNumberAlreadyExistsException,
			EmailAlreadyExistsException, PasswordAlreadyExistsException {

		if (service.findByName(user.getName()) != null) {
			throw new NameAlreadyExistsException("Name Already Exists.");
		}
		if (service.findByPhoneNumber(user.getPhoneNumber()) != null) {
			throw new PhoneNumberAlreadyExistsException("Phone Number Already Exists.");
		}
		if (service.findByEmail(user.getEmail()) != null) {
			throw new EmailAlreadyExistsException("Email ID Already Exists.");
		}
		if (service.findByPassword(user.getPassword()) != null) {
			throw new PasswordAlreadyExistsException("Password Already Exists.");
		} else {
			return service.addUser(user);
		}

	}

	@PutMapping("/update-password/{id}")
	public ResponseEntity<String> updatePassword(@Valid @PathVariable long id, @RequestBody User user)
			throws UserNotFoundException, PasswordAlreadyExistsException {

		if (service.findUserById(id) == null) {
			throw new UserNotFoundException("User does not exist.");
		}
		if (service.findByPassword(user.getPassword()) != null) {
			throw new PasswordAlreadyExistsException("Password already exists.");
		} else {

			service.updatePassword(id, user.getPassword());
			return new ResponseEntity<String>("Password has been successfully updated.", HttpStatus.OK);
		}
	}

	@PutMapping("/update-phone/{id}")
	public ResponseEntity<String> updatePhoneNumber(@Valid @PathVariable long id, @RequestBody User user)
			throws UserNotFoundException, PhoneNumberAlreadyExistsException {

		if (service.findUserById(id) == null) {
			throw new UserNotFoundException("User does not exist.");
		}
		if (service.findByPhoneNumber(user.getPhoneNumber()) != null) {
			throw new PhoneNumberAlreadyExistsException("Phone number already exists.");
		} else {
			service.updatePhoneNumber(id, user.getPhoneNumber());

			return new ResponseEntity<String>("Phone number successfully updated.", HttpStatus.OK);
		}

	}

	@PutMapping("/update-email/{id}")
	public ResponseEntity<String> updateEmailId(@Valid @PathVariable long id, @RequestBody User user)
			throws EmailAlreadyExistsException, UserNotFoundException {

		if (service.findUserById(id) == null) {
			throw new UserNotFoundException("User does not exist.");
		}
		if (service.findByEmail(user.getEmail()) != null) {
			throw new EmailAlreadyExistsException("Email ID already exists.");
		} else {
			service.updateEmailId(id, user.getEmail());
			return new ResponseEntity<String>("Email ID successfully updated.", HttpStatus.OK);
		}

	}

	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable long id) throws UserNotFoundException {

		User user = service.findUserById(id);

		if (user == null) {
			throw new UserNotFoundException("User does not exist.");
		} else {
			return user;
		}
	}

	@GetMapping("/user-account/{id}")
	public ResponseTemplateVO getUserAndAccountById(@PathVariable long id)
			throws UserNotFoundException, AccountNotCreatedException {

		User user = service.findUserById(id);

		if (user == null) {
			throw new UserNotFoundException("User ID does not exist.");
		}
		if (user.getAccountId() == 0) {
			throw new AccountNotCreatedException("Account hasn't been created for this user ID.");
		} else {
			return service.getUserAndAccountById(id);
		}
	}

}
