package com.api.service.account;

import java.util.List;

import com.api.entity.account.OpeningBal;

public interface OpeningBalService {
	
	public List<OpeningBal> getAllData();
	
	public OpeningBal getByIdData(long id);
	
	public OpeningBal postData(OpeningBal openingBal);
	
	public void deleteData(long id);
	
}
