package com.api.entity.account;

import java.sql.Date;

import com.api.entity.School;
import com.api.entity.Staff;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class CashPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long entryNo;

	private Date entryDate;

	@ManyToOne
	@JoinColumn(name = "cust_id")
	private CustomerMaster custId;

	private String tranType;

	private double Amount;

	private String narr;

	@ManyToOne
	@JoinColumn(name = "school_udise")
	private School schoolUdise;

	private String year;

	@ManyToOne
	@JoinColumn(name = "head_id")
	private HeadMaster headId;

	@ManyToOne
	@JoinColumn(name = "subhead_id")
	private SubHeadMaster subheadId;

//	user_id

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "staff_id")
	private Staff staffId;

	private Date createDate;

	private Date modifieDate;

	private long billNo;

	private String billType;
	
	private String status;

//  per id

//	per head

}
