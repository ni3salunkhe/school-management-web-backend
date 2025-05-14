package com.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.ModuleTransaction;
import com.api.entity.School;
import com.api.repository.ModuleTransactionRepository;
import com.api.service.ModuleTransactionService;

@Service
public class ModuleTransactionServiceImpl implements ModuleTransactionService {

	@Autowired
	private ModuleTransactionRepository moduleTransactionRepository;
	
	@Override
	public List<ModuleTransaction> getAllData() {
		// TODO Auto-generated method stub
		return moduleTransactionRepository.findAll();
	}

	@Override
	public ModuleTransaction getById(long id) {
		// TODO Auto-generated method stub
		return moduleTransactionRepository.findById(null).orElse(null);
	}

	@Override
	public ModuleTransaction post(ModuleTransaction moduleTransaction) {
		// TODO Auto-generated method stub
		return moduleTransactionRepository.save(moduleTransaction);
	}

	@Override
	public void deleteDataById(long id) {
		// TODO Auto-generated method stub

		moduleTransactionRepository.deleteById(id);
	}

	@Override
	public List<ModuleTransaction> getBySchoolUdise(School school) {
		// TODO Auto-generated method stub
		return moduleTransactionRepository.findByschool(school);
	}


}
