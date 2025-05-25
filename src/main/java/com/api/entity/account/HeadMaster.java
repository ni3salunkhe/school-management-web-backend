package com.api.entity.account;

import com.api.entity.School;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "head_master")
@Data
public class HeadMaster {
	
	@Id
	private long headId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "school_udise")
	private School schoolUdise;
	
	private String head_name;
}
