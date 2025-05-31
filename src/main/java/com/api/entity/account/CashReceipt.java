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
public class CashReceipt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long entryNo;

	private Date entryDate;

	@ManyToOne
	@JoinColumn(name = "cust_id")
	private CustomerMaster custId;

	private String tranType;

	private double amount;

	private String narr;

	@ManyToOne
	@JoinColumn(name = "school_udise")
	private School schoolUdise;

	private long year;

	@ManyToOne
	@JoinColumn(name = "head_id")
	private HeadMaster headId;

	@ManyToOne
	@JoinColumn(name = "subhead_id")
	private SubHeadMaster subheadId;

	// user id

	private Date createDate;

	private Date modifieDate;

	private long billNo;

	private String billType;

	private String saleDup;

	private String status;

	// site id

}
