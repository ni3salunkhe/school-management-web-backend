package com.api.dto.account;

import java.sql.Date;

import lombok.Data;

@Data
public class OpeningBalDto {

	private long id;

	private long custId;

	private Date date;

	private long crAmt;

	private long drAmt;

	private long amount;

	private Date year;

	private long headId;

}
