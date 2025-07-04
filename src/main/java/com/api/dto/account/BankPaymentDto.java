package com.api.dto.account;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BankPaymentDto {
	
	private long entryNo;

	private Date entryDate;

	private long custId;

	private String tranType;

	private double Amount;

	private String narr;
	
	private long bankId;

	private long schoolUdise;

	private String year;

	private long headId;

	private long subheadId;

//	user_id

	private long staffId;

	private Date createDate;

	private Date modifieDate;

	private String paymentType;

//	img
	
	private MultipartFile img;

//	bank head id

//	bank sub id

	private long billNo;

	private String status;
	
	private long bankSubheadId;

}
