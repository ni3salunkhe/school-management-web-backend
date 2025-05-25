package com.api.entity.account;

import com.api.entity.School;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CustomerMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long custId;

	private String custName;

	private String custAddress;
	
	private String contactPerson;

	private String eMail;

	private String custMob1;

	private String custMob2;

	private double crAmt;

	private double drAmt;
	
	@ManyToOne
	@JoinColumn(name = "school_udise")
	private School schoolUdise;
	
	@ManyToOne
	@JoinColumn(name = "head_id")
	private HeadMaster headId;
}
