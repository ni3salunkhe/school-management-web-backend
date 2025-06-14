package com.api.dto.account;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class JournalPaymentDto {

	private long id;

	private Date entryDate;

//	@ManyToOne
//	@JoinColumn(name = "cust_id")
//	private CustomerMaster custId;

	private String tranType;

	private double cramount;

	private String narr;

	private long schoolUdise;

	private long headId;

	private long creditAccount;
	
	private List<Entry> entries;
	
	@Data
	public static class Entry{
		private long debitaccount;
		
		private double amount;
	}

}
