package com.api.service.account;

import java.util.List;

import com.api.entity.account.YearMaster;

public interface YearMasterService {
	
	public List<YearMaster> getAllData();
	
	public YearMaster getDataById(long id);
	
	public YearMaster postData(YearMaster yearMaster);
	
	public void deleteData(long id);
 	
}
