package com.api.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SchoolDto {

	private long udiseNo;

	private String schoolSlogan;
	private String sansthaName;
	private String schoolName;
	
	private long state;
	
	private long district;
	
	private long tehsil;
	
	private long village;

	private long pinCode;
	private String medium;
	private String headMasterName;
	private String headMasterMobileNo;
	private String headMasterPassword;
	private String schoolPlace;
	private String board;
	private String boardDivision;
	private long boardIndexNo;
	private String schoolEmailId;
	private long schoolApprovalNo;
	private Date createdAt;

	
	private MultipartFile logo;
}
