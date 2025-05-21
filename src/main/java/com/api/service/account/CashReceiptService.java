package com.api.service.account;

import java.util.List;

import com.api.entity.School;
import com.api.entity.account.CashReceipt;

public interface CashReceiptService {

	public List<CashReceipt> getAllData();

	public CashReceipt getById(long id);

	public CashReceipt postData(CashReceipt cashReceipt);

	public void deleteData(long id);

	public List<CashReceipt> getbySchoolUdise(School school);
}
