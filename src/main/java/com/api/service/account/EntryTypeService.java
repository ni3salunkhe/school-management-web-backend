package com.api.service.account;

import java.util.List;
import java.util.Optional;

import com.api.entity.account.EntryType;

public interface EntryTypeService {

	public EntryType post(EntryType entryType);
	
	public List<EntryType> getdata();
	
	public List<EntryType> getdatabyudiseno(long udiseNo);
	
	public EntryType getById(long id);
	
	public void deletbyid(long id);
	
}
