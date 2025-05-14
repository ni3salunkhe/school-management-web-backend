package com.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.ModuleMaster;
import com.api.repository.ModuleMasterRepository;
import com.api.service.ModuleMasterService;

@Service
public class ModuleMasterServiceImpl implements ModuleMasterService {

	@Autowired
	private ModuleMasterRepository moduleMasterRepository;
	
	@Override
	public List<ModuleMaster> getAllData() {
		// TODO Auto-generated method stub
		return moduleMasterRepository.findAll();
	}

	@Override
	public ModuleMaster getById(long id) {
		// TODO Auto-generated method stub
		return moduleMasterRepository.findById(id).orElse(null);
	}

	@Override
	public ModuleMaster post(ModuleMaster moduleMaster) {
		// TODO Auto-generated method stub
		return moduleMasterRepository.save(moduleMaster);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		moduleMasterRepository.deleteById(id);
	}

}
