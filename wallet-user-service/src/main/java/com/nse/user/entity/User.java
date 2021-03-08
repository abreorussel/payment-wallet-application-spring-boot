package com.nse.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	@Pattern(regexp = "^[A-Z][a-zA-z]+([\\s][A-Z][a-zA-Z]+)+$")
	private String name;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&-+=()])(?=\\S+$).{8,20}$")
	private String password;
	
	@Pattern(regexp = "[7-9][0-9]{9}")
	private String phoneNumber;
	
	@Email
	private String email;
	
	private long accountId;
	
	
}
