package com.nse.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nse.user.entity.User;
import com.nse.user.repository.UserRepository;
import com.nse.user.vo.Account;
import com.nse.user.vo.ResponseTemplateVO;


@Service
public class UserService implements IUserService {

	
	@Autowired
	UserRepository repo;
	
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	@Override
	public User addUser(User user) {
		
		return repo.save(user);
	}


	@Override
	public User findByName(String name) {
		
		return repo.findByName(name);
	}


	@Override
	public User findByPhoneNumber(String phone) {
		
		return repo.findByPhoneNumber(phone);
	}


	@Override
	public User findByEmail(String email) {
		
		return repo.findByEmail(email);
	}


	@Override
	public User findByPassword(String password) {
		
		return repo.findByPassword(password);
	}


	@Override
	public void updatePassword(long id, String password) {
		repo.updatePassword(id, password);
		
	}


	@Override
	public void updatePhoneNumber(long id, String phone) {
		repo.updatePhoneNumber(id, phone);
		
	}


	@Override
	public void updateEmailId(long id, String email) {
		repo.updateEmailId(id, email);
		
	}


	@Override
	public User findUserById(long id) {
		
		return repo.findById(id).orElse(null);
	}


	@Override
	public ResponseTemplateVO getUserAndAccountById(long id) {
		
		
		ResponseTemplateVO vo = new ResponseTemplateVO();
		
		User user = repo.findById(id).orElse(null);
		
		Account account = webClientBuilder.build()
				.get()  
				.uri("http://ACCOUNT-SERVICE/api/account/"+user.getAccountId())   
				.retrieve()
				.bodyToMono(Account.class)       
				.block();
		
		vo.setAccount(account);
		vo.setUser(user);
		
		return vo;
	}






}
