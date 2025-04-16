package com.api.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
<<<<<<< HEAD
import jakarta.persistence.FetchType;
=======
>>>>>>> daa3f1e132236efd940915c9b3a2134fc7401fc1
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
<<<<<<< HEAD
	
=======
>>>>>>> daa3f1e132236efd940915c9b3a2134fc7401fc1
	private long registerNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_udise_no", nullable = false)
	private School school;
	
	private String apparId;
	
	private String studentId;
	
	private String adhaarNumber;
	
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
	private String birthPlace;
	
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
	private String ebcInformation;
	private String minorityInformation;
	private String casteCategory;
	
}
