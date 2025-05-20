package com.api.entity.account;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class OpeningBal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private CustomerMaster custId;
	
	private Date date;
	
	private long crAmt;
	
	private long drAmt;
	
	private long amount;
	
	private Date year;
	@ManyToOne
	private HeadMaster headId;
	
}
