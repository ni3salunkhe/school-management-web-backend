package com.api.service.account;

import java.util.List;

import com.api.entity.account.CustomerMaster;

public interface CustomerMasterService {

	public List<CustomerMaster> getAllMasters();
	
	public CustomerMaster getById(long id);
	
	public CustomerMaster postData(CustomerMaster customerMaster);
	
	public void deleteData(long id);
	
}
