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
public class ExpensesVouchar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long entryNo;
	
	private Date entryDate;

	private String tranType;

	private double Amount;

	private String narr;
	
	private String voucharType;

	@ManyToOne
	@JoinColumn(name = "school_udise")
	private School schoolUdise;
	
	@ManyToOne
	@JoinColumn(name = "head_id")
	private HeadMaster headId;

	@ManyToOne
	@JoinColumn(name = "subhead_id")
	private SubHeadMaster subheadId;
	
	private Date createDate;
	
	private long custId;
	
}
