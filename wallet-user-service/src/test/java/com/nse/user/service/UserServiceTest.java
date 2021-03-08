package com.nse.user.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nse.user.entity.User;
import com.nse.user.vo.Account;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

	@Autowired
	IUserService service;

	@Autowired
	User user;
	
	
	@Test
	void testAddUser() {
		
		user.setName("Bruce Wayne");
		user.setEmail("bruce@gmail.com");
		user.setPassword("#Bruce23232");
		user.setPhoneNumber("8980090988");
		
		User user1 = service.addUser(user);
		
		Assertions.assertEquals(user.toString(), user1.toString());
		
	}

	@Test
	void testFindByName() {
		user.setUserId(86L);
		user.setAccountId(89L);
		user.setEmail("tim22@gmail.com");
		user.setName("Tim Dsouza");
		user.setPassword("#Tim38383");
		user.setPhoneNumber("8564432733");
		
		Assertions.assertEquals(user.toString(), service.findByName(user.getName()).toString());
		
	}

	@Test
	void testFindByPhoneNumber() {
		
		user.setUserId(86L);
		user.setAccountId(89L);
		user.setEmail("tim22@gmail.com");
		user.setName("Tim Dsouza");
		user.setPassword("#Tim38383");
		user.setPhoneNumber("8564432733");
		
		Assertions.assertEquals(user.toString(), service.findByPhoneNumber(user.getPhoneNumber()).toString());
	}

	@Test
	void testFindByEmail() {
		
		user.setUserId(85L);
		user.setAccountId(0L);
		user.setEmail("john@gmail.com");
		user.setName("John Davis");
		user.setPassword("#John5353");
		user.setPhoneNumber("8676673644");
		
		
		Assertions.assertEquals(user.toString(), service.findByEmail(user.getEmail()).toString());
	}

	@Test
	void testFindByPassword() {
		
		user.setUserId(85L);
		user.setAccountId(0L);
		user.setEmail("john@gmail.com");
		user.setName("John Davis");
		user.setPassword("#John5353");
		user.setPhoneNumber("8676673644");
		
		Assertions.assertEquals(user.toString(), service.findByPhoneNumber(user.getPhoneNumber()).toString());
	}


	@Test
	void testUpdateEmailId() {
		
		user.setUserId(87L);
		user.setEmail("henrycavill@gmail.com");
		
		service.updateEmailId(user.getUserId(), user.getEmail());
		
		Assertions.assertEquals(user.getEmail() , service.findUserById(user.getUserId()).getEmail());
		
	}

	@Test
	void testFindUserById() {
		
		user.setUserId(89);
		Assertions.assertNull(service.findUserById(user.getUserId()), "User ID does not exist");
	}


}
