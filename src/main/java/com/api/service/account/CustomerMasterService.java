package com.api.service.account;

import java.util.List;

import com.api.entity.School;
import com.api.entity.account.CustomerMaster;

public interface CustomerMasterService {

	public List<CustomerMaster> getAllMasters();
	
	public CustomerMaster getById(long id);
	
	public CustomerMaster postData(CustomerMaster customerMaster);
	
	public void deleteData(long id);
	
	public List<CustomerMaster> getByUdise(School school);
	
	public List<CustomerMaster> getonlycustomerbyUdise(long udise);
	
	public CustomerMaster getCashInHandCustomerByUdise(String customer,long udise);
	
	public List<CustomerMaster> getDataByHeadNameAndUdise(long udise,String headName);
	
}
