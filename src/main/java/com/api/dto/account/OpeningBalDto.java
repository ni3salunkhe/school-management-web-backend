package com.api.dto.account;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class OpeningBalDto {

	private long id;

	private long custId;

	private Date date;

	private double crAmt;

	private double drAmt;

	private double amount;

	private String year;

	private long headId;
	
	private long subHeadId;
	
	private String financialYear;
    
	private List<BalanceEntry> balances;
    	
	 @Data
	 public static class BalanceEntry {
	        private Long headId;
	        private Long subHeadId;
	        private Double amount;
	        private String balanceType;
	        private long udiseNo;
	        private String year;
	 }

}
