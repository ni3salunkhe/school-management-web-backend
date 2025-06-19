package com.api.dto.account;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class JournalPaymentDto {

	 private long id;
	    private Date entryDate;
	    private long transactionKey;
	    private String tranType;
	    private double dramount;
	    private String narr;
	    private long schoolUdise;
	    private long headId;
	    
	    // Changed from creditAccount to debitaccount
	    private long debitaccount;
	    
	    private List<Entry> entries;
	    
	    @Data
	    public static class Entry {
	        // Changed from debitaccount to creditAccount
	        private long creditAccount;
	        private double amount;
	    }
}
