package com.api.entity.account;

import com.api.entity.School;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class SubHeadMaster {
	
	@Id
	private long subHeadId;
	
	private String subHeadName;
	
	@ManyToOne
	@JoinColumn(name = "head_id")
	private HeadMaster headId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "school_udise")
	private School schoolUdise;
	
}
