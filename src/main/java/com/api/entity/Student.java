package com.api.entity;

import java.sql.Date;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Student {
	
	@Id
	private long registerNumber;
	
	@ManyToOne
	private School school;
	
	private long apparId;
	
	private long studentId;
	
	private long adhaarNumber;
	
	private String gender;
	
	private String surName;
	private String studentName;
	private String fatherName;
	private String motherName;
	private String nationality;
	private String motherTongue;
	private String religion;
	private String caste;
	private String subCast;
	private String residentialAddress;
	private String mobileNo;
	@ManyToOne
	private Village villageOfBirth;
	@ManyToOne
	private Tehsil tehasilOfBirth;
	@ManyToOne
	private District districtOfBirth;
	@ManyToOne
	private State stateOfBirth;
	
	private Date dateOfBirth;
	private String dateOfBirthInWord;
	private String lastSchoolUdiseNo;
	private Date admissionDate;
	private String whichStandardAdmitted;
	private Date createdAt;
	private String currentAcadmicYear;
	private String ebcInformation;
	private String minorityInformation;
	
}
