package com.api.dto.account;

import lombok.Data;

@Data
public class CustomerMasterDto {

	private long custId;

	private String custName;

	private String custAddress;
	
	private String contactPerson;

	private String eMail;

	private String custMob1;

	private String custMob2;

	private double crAmt;

	private double drAmt;

	private long schoolUdise;

	private long custTypeID;

	private String status;

	private long pinCode;

	private String gstin;

	private String panNo;

}
