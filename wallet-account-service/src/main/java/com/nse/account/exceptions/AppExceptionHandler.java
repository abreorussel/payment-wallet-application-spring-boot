package com.nse.account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(MinimumBalanceException.class)
	public ResponseEntity<String> minimumBalanceException(MinimumBalanceException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccountIdNotFoundException.class)
	public ResponseEntity<String> accountIdNotFoundException(AccountIdNotFoundException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<String> transactionNotFoundException(TransactionNotFoundException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(EmptyTableException.class)
	public ResponseEntity<String> emptyTableException(EmptyTableException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundException(UserNotFoundException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(AccountAlreadyExistsException.class)
	public ResponseEntity<String> accountAlreadyExistsException(AccountAlreadyExistsException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
	
}
