package com.api.service.account;

import java.util.List;

import com.api.entity.account.CustomerTypeMaster;

public interface CustomerTypeMasterService {
	
	public List<CustomerTypeMaster> allData();
	
	public CustomerTypeMaster getById(long id);
	
	public CustomerTypeMaster postData(CustomerTypeMaster customerTypeMaster);
	
	public void deleteData(long id);
	
}
