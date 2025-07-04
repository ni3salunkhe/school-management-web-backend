package com.api.entity.account;

import java.sql.Date;

import com.api.entity.School;
import com.api.entity.Staff;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	
	private double Amount;
	
	private String narr;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_id")
	private BankMaster bankId;
	
	@ManyToOne
	private School schoolUdise;
	
	private String year;
	
	@ManyToOne
	private HeadMaster headId;
	
	@ManyToOne
	private SubHeadMaster subheadId;
	
//	user_id
	
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staffId;
	
	private Date createDate;
	
	private Date modifieDate;
	
	private String paymentType;
	
//	img
	
	@Lob
	@Column(name = "img" ,columnDefinition = "LONGBLOB")
	private byte[] img;
	
//	bank head id
	
//	bank sub id
	
	private long billNo;
	
	private String status;
	
}
