package com.api.service.account;

import java.util.List;

import com.api.entity.account.GeneralLedger;

public interface GeneralLedgerService {
	public GeneralLedger post(GeneralLedger generalLedger);
	
	public List<GeneralLedger> getbyudise(long udiseNo);
	
	public List<GeneralLedger> getdata();

	GeneralLedger getbyid(long entryNo); 
}
