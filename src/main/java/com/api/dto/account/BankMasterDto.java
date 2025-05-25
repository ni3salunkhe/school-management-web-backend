package com.api.dto.account;

import lombok.Data;

@Data
public class BankMasterDto {
	
	private int id;
	
	private long udiseNo;
	
	private String bankname;
	
	private String ifsccode;
	
	private String branch;
	
	private String address;
	
	private String accountno;
	
	private int accounttype;
		
}
