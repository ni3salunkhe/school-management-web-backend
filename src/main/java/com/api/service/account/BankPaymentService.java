package com.api.service.account;

import java.util.List;

import com.api.entity.School;
import com.api.entity.account.BankPayment;

public interface BankPaymentService {
	
	public List<BankPayment> getAllData();
	
	public BankPayment getById(long id);
	
	public BankPayment postData(BankPayment bankPayment);
	
	public void deleteData(long id);
	
	public List<BankPayment> getbySchoolUdise(School school);
	
}
