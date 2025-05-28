package com.api.dto.account;

import java.sql.Date;

import com.api.entity.Staff;
import com.api.entity.account.HeadMaster;
import com.api.entity.account.SubHeadMaster;

import lombok.Data;

@Data
public class CashReceiptDto {

	private Date entryDate;

	private long custId;

	private String tranType;

	private double Amount;

	private String narr;

	private long schoolUdise;

	private String year;

	private HeadMaster headId;

	private SubHeadMaster subheadId;

//	user_id

	private Staff staffId;

	private Date createDate;

	private Date modifieDate;

	private long billNo;

	private String billType;

	private String status;

}
