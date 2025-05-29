package com.api.dto.account;

import java.sql.Date;
import lombok.Data;

@Data
public class CashPaymentDto {

	private long entryNo;

	private Date entryDate;
	
	private int custId;

	private String tranType;

	private double amount;

	private String narr;
	
	private long schoolUdise;

	private String year;
	
	private int headId;
	
	private int subheadId;
	
	private int staffId;

	private Date createDate;

	private Date modifieDate;

	private long billNo;

	private String billType;
	
	private String status;
	
}
