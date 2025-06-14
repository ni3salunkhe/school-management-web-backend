package com.api.service.account;

import java.math.BigDecimal;
import java.util.List;

import com.api.entity.account.CustomerMaster;
import com.api.entity.account.GeneralLedger;
import com.api.entity.account.HeadMaster;

public interface GeneralLedgerService {
	public GeneralLedger post(GeneralLedger generalLedger);
	
	public List<GeneralLedger> getbyudise(long udiseNo);
	
	public List<GeneralLedger> getdata();

	GeneralLedger getbyid(long entryNo); 

	public List<GeneralLedger> getbysubhead(long subhead);
	
	public BigDecimal getBalanceBySubhead(Long subhead);
	
	public List<GeneralLedger> getByBookTypeName(String headName,long id);
	
	public List<GeneralLedger> getByHeadId(HeadMaster headMaster);
	
}
