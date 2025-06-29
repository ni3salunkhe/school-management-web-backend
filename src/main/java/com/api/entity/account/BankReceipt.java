package com.api.entity.account;

import java.sql.Date;

import com.api.entity.School;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private CustomerMaster custId;
	
	private String tranType;
	
	private double amount;
	
	private String narr;
	
	//bank id
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_id")
	private BankMaster bankId;
	
	private String year;
	
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
	@Lob
	@Column(name = "img" ,columnDefinition = "LONGBLOB")
	private byte[] img;
	
	//bank head id
	
	//bank sub head id
	
	private long billNo;
	
	private String billType;
	
	private String status;
	
	//site id
}
