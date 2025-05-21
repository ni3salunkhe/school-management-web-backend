package com.api.entity.account;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class OpeningBal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@JoinColumn(name =  "cust_id")
	private CustomerMaster custId;

	private Date date;

	private double crAmt;

	private double drAmt;

	private double amount;

	private Date year;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "head_id")
	private HeadMaster headId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subhead_id")
	private SubHeadMaster subHeadId;
	
}
