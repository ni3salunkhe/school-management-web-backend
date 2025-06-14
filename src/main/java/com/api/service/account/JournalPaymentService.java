package com.api.service.account;

import java.util.List;

import com.api.entity.account.JournalPayment;

public interface JournalPaymentService {
	
	public List<JournalPayment> getAllData();
	
	public JournalPayment getById(long id);
	
	public JournalPayment postData(JournalPayment journalPayment);
	
	public void deleteData(long id);
	
	public List<JournalPayment> getDataByUdise(long udiseNo);
	
}
