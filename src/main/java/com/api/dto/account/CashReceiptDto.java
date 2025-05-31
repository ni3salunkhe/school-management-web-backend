package com.api.dto.account;

import java.sql.Date;

import com.api.entity.Staff;

import lombok.Data;

@Data
public class CashReceiptDto {

	private int entryNo;
	
	private Date entryDate;

	private long custId;

	private String tranType;

	private double Amount;

	private String narr;

	private long schoolUdise;

	private String year;

	private long headId;

	private long subheadId;

//	user_id

	private Staff staffId;

	private Date createDate;

	private Date modifieDate;

	private long billNo;

	private String billType;
	
	private String saleDup;

	private String status;

}
