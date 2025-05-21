package com.api.service.account;

import java.util.List;

import com.api.entity.School;
import com.api.entity.account.BankReceipt;

public interface BankReceiptService {
	
	public List<BankReceipt> getAllData();
	
	public BankReceipt getById(long id);
	
	public BankReceipt postData(BankReceipt bankReceipt);
	
	public void deleteData(long id);
	
	public List<BankReceipt> getbySchoolUdise(School school);
}
