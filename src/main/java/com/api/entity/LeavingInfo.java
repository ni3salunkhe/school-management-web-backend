package com.api.entity;

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
public class LeavingInfo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "studentRegistrationNo" ,referencedColumnName = "id")
	private Student studentRegisterNo;
	
	
	
	private String reasonOfLeavingSchool;
	
	private String progress;
	
	private String behavior;
	
	private Date dateOfLeavingSchool;
	
	private String remark;
	
	private String lcNumber;
	
	private Date lcDate;
	
	private String otherRemark;
	
	private Date createdAt;
	
	
}
