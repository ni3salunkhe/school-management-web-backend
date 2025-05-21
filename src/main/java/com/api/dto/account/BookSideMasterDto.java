package com.api.dto.account;

import java.sql.Date;

import lombok.Data;

@Data
public class BookSideMasterDto {

	private long booksideId;

	private String booksideName;

	private long schoolUdise;

	private long booktypeId;

	private Date entryDate;

}
