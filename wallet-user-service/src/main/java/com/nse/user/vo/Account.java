package com.nse.user.vo;

import javax.persistence.EnumType;

import com.nse.user.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

	
	private long accountId;
	private double accountBalance;
	private Status status;
	private long userId;
}
