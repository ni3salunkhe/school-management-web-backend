package com.api.service.account;

import java.util.List;


import com.api.entity.account.BankMaster;

public interface BankMasterService {
	
	public BankMaster post(BankMaster bankMaster);
	
	public BankMaster getbyid(long id);
	
	public List<BankMaster> getdata();
	
	public void deletebyid(long id);
	
	public List<BankMaster> getbyudiseno(long udiseNo);
	
}
