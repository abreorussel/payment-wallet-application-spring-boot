package com.nse.account.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.nse.account.entity.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	//get the number of rows
	@Transactional
	@Modifying
	@Query(value =  "SELECT COUNT(*) FROM TRANSACTION WHERE ACCOUNT_ID = ?1" , nativeQuery = true)
	List<Integer> getNumberofRows(long id);
	
	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE USER SET ACCOUNT_ID = ?1 WHERE USER_ID = ?2" , nativeQuery =  true)
	void updateAccountId(long accountId , long userId);

	
	@Transactional
	@Modifying
	@Query(value = "SELECT USER_ID FROM USER WHERE USER_ID = ?1" , nativeQuery = true)
	List<Long> getUserId(long id);
	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE ACCOUNT SET ACCOUNT_BALANCE = ?2 WHERE ACCOUNT_ID = ?1" , nativeQuery = true)
	void updateAccountBalanceById(long id , double balance);
	
	
	Account findByUserId(long id);
	
	@Transactional
	@Modifying
	@Query(value = "SELECT ACCOUNT_ID FROM USER WHERE USER_ID = ?1" , nativeQuery = true)
	List<Long> getAccountIdFromUser(long id);
}

