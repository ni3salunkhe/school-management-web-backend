package com.api.entity.account;

import java.sql.Date;

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
public class BankReceipt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long entryNo;
	
	private Date entryDate;
	
	//party id
	
	private String tranType;
	
	private double amount;
	
	private String narr;
	
	//bank id
	
	private long year;
	
	@ManyToOne
	@JoinColumn(name = "school_udise")
	private School schoolUdise;
	
	@ManyToOne
	@JoinColumn(name = "head_id")
	private HeadMaster headId;
	
	@ManyToOne
	@JoinColumn(name ="subhead_id")
	private SubHeadMaster subheadId;
	
	//user id
	
	private Date createDate;
	
	private Date modifieDate;
	
	private String paymentType;
	
	//img
	
	//bank head id
	
	//bank sub head id
	
	private String billNo;
	
	private String billType;
	
	private String status;
	
	//site id
}
