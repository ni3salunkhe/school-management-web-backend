package com.api.dto.account;
import lombok.Data;

@Data
public class CustomerMasterDto {

	private long custId;

	private String custName;

	private String custAddress;

	private String custMob1;

	private String custMob2;

	private long crAmt;

	private long drAmt;

	private long  headId;

}
