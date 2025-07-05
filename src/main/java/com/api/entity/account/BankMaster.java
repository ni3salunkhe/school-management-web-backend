package com.api.entity.account;

import com.api.entity.School;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class BankMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "SchoolUdiseNo")
	private School schoolUdiseNo;
	
	private String bankname;
	
	private String ifsccode;
	 
	private String branch;
	
	private String address;
	
	private String accountno;
	
	@ManyToOne
	@JoinColumn(name = "head_id")
	private HeadMaster headId;
	
	@ManyToOne
	@JoinColumn(name="AccountTypeId")
	private AccountType accounttype;
	
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private CustomerMaster custId;
	
	@ManyToOne
	@JoinColumn(name = "subhead_id")
	private SubHeadMaster subHeadId;
}
