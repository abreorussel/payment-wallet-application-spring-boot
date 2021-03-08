package com.nse.transaction.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nse.transaction.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("from Transaction where account_id = ?1" )
	List<Transaction> getTransactionHistory(long id);
	
	
	@Query(value = "SELECT ACCOUNT_ID FROM ACCOUNT WHERE ACCOUNT_ID = ?1" , nativeQuery = true) 
	List<Long> getAccountId(long id);
	
	
	@Query(value = "SELECT ACCOUNT_BALANCE FROM ACCOUNT WHERE ACCOUNT_ID = ?1" , nativeQuery = true)
	List<Double> getAccountBalanceById(long id);
	
	
	//updating sender balance in account
	@Transactional
	@Modifying
	@Query(value = "UPDATE ACCOUNT SET ACCOUNT_BALANCE = ?2 WHERE ACCOUNT_ID = ?1" , nativeQuery = true)
	void updateSenderAccountBalanceById(long id , double amount);
	
	
	//updating sender balance in transaction
	@Transactional
	@Modifying
	@Query(value = "UPDATE TRANSACTION SET ACCOUNT_BALANCE = ?2 WHERE TRANSACTION_ID = ?1" ,nativeQuery = true)
	void updateSenderTransactionBalanceById(long id , double amount);
	
	
	//updating receiver balance in account
	@Transactional
	@Modifying
	@Query(value =  "UPDATE ACCOUNT SET ACCOUNT_BALANCE = ?2 WHERE ACCOUNT_ID = ?1" , nativeQuery = true)
	void updateRecieverBalanceById(long id , double amount);
	
	
	//get the number of rows
	@Query(value =  "SELECT COUNT(*) FROM TRANSACTION" , nativeQuery = true)
	List<Integer> getNumberofRows();
	
	
	Long countByAccountId(long id);
	
}
