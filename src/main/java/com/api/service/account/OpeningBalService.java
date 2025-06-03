package com.api.service.account;

import java.util.List;
import java.util.Map;

import com.api.dto.account.OpeningBalDto;
import com.api.entity.account.OpeningBal;

public interface OpeningBalService {
	
	public List<OpeningBal> getAllData();
	
	public OpeningBal getByIdData(long id);
	
	public OpeningBal postData(OpeningBal openingBal);
	
	public void deleteData(long id);
	
	public void saveOpeningBalances(OpeningBalDto request);
	
	public Map<String, Double> getSumCrDr();
}
