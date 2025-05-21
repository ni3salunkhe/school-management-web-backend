package com.api.dto.account;

import java.sql.Date;
import lombok.Data;

@Data
public class BookTypeMasterDto {

	private long booktypeId;

	private long school_udise;

	private String booktypeName;

	private Date entryDate;
}
