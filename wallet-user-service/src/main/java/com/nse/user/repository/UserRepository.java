package com.nse.user.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nse.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
	User findByName(String name);
	
	User findByPhoneNumber(String phone);
	
	User findByEmail(String email);
	
	User findByPassword(String password);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE USER SET PASSWORD = ?2 WHERE USER_ID = ?1" , nativeQuery = true)
	void updatePassword(long id, String password);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE USER SET PHONE_NUMBER = ?2 WHERE USER_ID = ?1" , nativeQuery = true)
	void updatePhoneNumber(long id ,String phone);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE USER SET EMAIL = ?2 WHERE USER_ID = ?1" , nativeQuery = true)
	void updateEmailId(long id, String email);
	
	
	
}
