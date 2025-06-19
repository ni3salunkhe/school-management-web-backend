package com.api.service.account;

import java.util.List;

import com.api.entity.account.ExpensesVouchar;

public interface ExpensesVoucharService {
	
	public List<ExpensesVouchar> getAllData();
	
	public ExpensesVouchar getById(long id);
	
	public ExpensesVouchar postData(ExpensesVouchar expensesVouchar);
	
	public void deleteData(long id);
	
	public List<ExpensesVouchar> getDataByUdiseNo(long UdiseNo);
	
}
