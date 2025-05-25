package com.api.entity.account;

import com.api.entity.School;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class AccountType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountTypeId;
	
	@ManyToOne
	@JoinColumn(name="SchoolUdiseNo")
	private School schoolUdiseNo;
	
	private String name;
}
