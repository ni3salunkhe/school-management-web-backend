package com.api.service;

import java.util.List;

import com.api.entity.ModuleTransaction;
import com.api.entity.School;

public interface ModuleTransactionService {
	
	public List<ModuleTransaction> getAllData();
	
	public ModuleTransaction getById(long id);
	
	public ModuleTransaction post(ModuleTransaction moduleTransaction);
	
	public void deleteDataById(long id);
	
	public List<ModuleTransaction> getBySchoolUdise(School school);
	
}
