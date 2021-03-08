package com.nse.transaction.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(TransactionIdNotFoundException.class)
	public ResponseEntity<String> transactionIdNotFound(TransactionIdNotFoundException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(AccountIdNotFoundException.class)
	public ResponseEntity<String> accountIdNotFoundException(AccountIdNotFoundException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MinimumBalanceException.class)
	public ResponseEntity<String> minimumBalanceException(MinimumBalanceException ex){
		
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
	
	
	@ExceptionHandler(InvalidTransactionException.class)
	public ResponseEntity<String> invalidTransactionException(InvalidTransactionException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(InvalidAccountIdException.class)
	public ResponseEntity<String> invalidAccountIdException(InvalidAccountIdException ex){
		
		return new ResponseEntity<String>(ex.getMessage() , HttpStatus.BAD_REQUEST);
	}
	
	
}
