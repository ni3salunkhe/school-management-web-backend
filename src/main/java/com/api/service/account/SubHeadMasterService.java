package com.api.service.account;

import java.util.List;

import com.api.entity.account.SubHeadMaster;

public interface SubHeadMasterService {
	
	public List<SubHeadMaster> getAllData();
	
	public SubHeadMaster getById(long id);
	
	public SubHeadMaster postData(SubHeadMaster subHeadMaster);
	
	public void deleteData(long id);
	
	public List<SubHeadMaster> getbyudiseno(long udiseNo);
}
