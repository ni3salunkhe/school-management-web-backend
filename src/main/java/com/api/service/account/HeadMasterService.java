package com.api.service.account;

import java.util.List;

import com.api.entity.account.HeadMaster;

public interface HeadMasterService {
	
	public List<HeadMaster> getAllData();
	
	public HeadMaster getById(long id);
	
	public HeadMaster postData(HeadMaster headMaster);
	
	public void deleteData(long id);
	
	public List<HeadMaster> getByUdiseNo(long udiseNo);
	
	public HeadMaster getByHeadName(String headName);
	
	public List<HeadMaster> getByBookSideName(String BookSideName);
	
}
