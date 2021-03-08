package com.nse.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(NameAlreadyExistsException.class)
	public ResponseEntity<String> nameAlreadyExistsException(NameAlreadyExistsException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PhoneNumberAlreadyExistsException.class)
	public ResponseEntity<String> phoneNumberAlreadyExistsException(PhoneNumberAlreadyExistsException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<String> emailAlreadyExistsException(EmailAlreadyExistsException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PasswordAlreadyExistsException.class)
	public ResponseEntity<String> passwordAlreadyExistsException(PasswordAlreadyExistsException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundException(UserNotFoundException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(AccountNotCreatedException.class)
	public ResponseEntity<String> accountNotCreatedException(AccountNotCreatedException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
