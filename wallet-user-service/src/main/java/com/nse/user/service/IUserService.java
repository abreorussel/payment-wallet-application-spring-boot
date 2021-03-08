package com.nse.user.service;

import java.util.List;

import com.nse.user.entity.User;
import com.nse.user.vo.ResponseTemplateVO;

public interface IUserService {

	
	public User addUser(User user);
	
	public User findByName(String name);
	
	public User findByPhoneNumber(String phone);
	
	public User findByEmail(String email);
	
	public User findByPassword(String password);
	
	public void updatePassword(long id, String password);
	
	public void updatePhoneNumber(long id ,String phone);
	
	public void updateEmailId(long id, String email);
	
	public User findUserById(long id);
	
	public ResponseTemplateVO getUserAndAccountById(long id);
	
	
}
