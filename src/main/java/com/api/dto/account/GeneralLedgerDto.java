package com.api.dto.account;

import java.sql.Date;

import lombok.Data;

@Data
public class GeneralLedgerDto {
	private long entryNo;
	
	private long udiseNo;
	
	private Date entrydate;
	
	private int entryType;
	
	private int custid;
	
	private int year;
	
	private String narr;
	
	private int headid;
	
	private String daybookname;
	
	private Date salepaydate;
	
	private String salepaydup;
	
	private int subhead;
	
	private String entrynochar;
	
	private long billno;
	
	private long maxno;
	
	private Double Cr_Amt;
	
	private Double Dr_Amt;
	
	//perid
	
		//perhead
		
		//daybookid;
		
		//daybookheadid;
//	private int siteid;
	
	
}
