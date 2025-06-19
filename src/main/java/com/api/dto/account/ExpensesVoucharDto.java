package com.api.dto.account;

import java.sql.Date;

import lombok.Data;

@Data
public class ExpensesVoucharDto {

	private Date entryDate;

	private String tranType;

	private double Amount;

	private String narr;

	private long schoolUdise;

	private long headId;

	private long subheadId;

	private Date createDate;

}
