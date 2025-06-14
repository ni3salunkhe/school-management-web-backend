package com.api.dto.account;

import java.sql.Date;
import lombok.Data;

@Data
public class ContraPaymentDto {

	private long entryNo;
	
	private long udiseNo;
	
	private Date entrydate;
	
	private String entryType;
	
	private int custid;
	
	private int year;
	
	private String narr;
	
	private int headid;
	
	private String daybookname;

	private Date salepaydate;
	
	private String salepaydup;
	
	private long subhead;
	
	private String entrynochar;
	
	private long billno;
	
	private long maxno;
	
	private Double amount;
	
	private long mainCustId1;
	
	private long mainHeadId;
	
	private long mainSubHead;
	
	private long staffId;
	
}
