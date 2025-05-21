package com.api.service.account;

import java.util.List;

import com.api.entity.School;
import com.api.entity.account.CashPayment;

public interface CashPaymentService {
	
	public List<CashPayment> getAllData();
	
	public CashPayment getById(long id);
	
	public CashPayment postData(CashPayment cashPayment);
	
	public void deleteData(long id);
	
	public List<CashPayment> getbySchoolUdise(School school);
}
