package com.api.dto.account;

import java.sql.Date;


import lombok.Data;

@Data
public class OpeningBalDto {

	private long id;

	private long custId;

	private Date date;

	private double crAmt;

	private double drAmt;

	private double amount;

	private Date year;

	private long headId;
	
	private long subHeadId;

}
