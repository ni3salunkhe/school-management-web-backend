package com.api.entity.account;

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
	
	private String custMob1;
	
	private String custMob2;
	
	private long crAmt;
	
	private long drAmt;
	
	@ManyToOne
	@JoinColumn(name = "head_id")
	private HeadMaster headId;
}
