package com.api.entity.account;

import java.sql.Date;

import com.api.entity.School;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class BankPayment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long entryNo;
	
	private Date entryDate;
	
	@ManyToOne
	private CustomerMaster custId;
	
	private String tranType;
	
	private long Amount;
	
	private String narr;
	
	@ManyToOne
	private School schoolUdise;
	
	private Date year;
	
	@ManyToOne
	private HeadMaster headId;
	
	@ManyToOne
	private SubHeadMaster subheadId;
	
//	user_id
	
	private Date createDate;
	
	private Date modifieDate;
	
	private String paymentType;
	
//	img
	
//	bank head id
	
//	bank sub id
	
	private long billNo;
	
	
}
