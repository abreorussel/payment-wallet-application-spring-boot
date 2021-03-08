package com.nse.account.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.stereotype.Component;

import com.nse.account.enumeration.Status;
import com.nse.account.vo.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accountId;
	
	@Max(20000)
	@NotNull
	private double accountBalance;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.INACTIVE;
	
	@Positive
	@NotNull
	private long userId;
	
	
}
