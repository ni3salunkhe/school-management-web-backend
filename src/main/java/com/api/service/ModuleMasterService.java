package com.api.service;

import java.util.List;

import com.api.entity.ModuleMaster;

public interface ModuleMasterService {

	public List<ModuleMaster> getAllData();
	
	public ModuleMaster getById(long id);
	
	public ModuleMaster post(ModuleMaster moduleMaster);
	
	public void deleteData(long id);
	
}
