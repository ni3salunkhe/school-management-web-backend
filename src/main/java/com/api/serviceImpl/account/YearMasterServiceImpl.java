package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.account.YearMaster;
import com.api.repository.account.YearMasterRepository;
import com.api.service.account.YearMasterService;

@Service
public class YearMasterServiceImpl implements YearMasterService{

	@Autowired
	private YearMasterRepository yearMasterRepository;
	
	@Override
	public List<YearMaster> getAllData() {
		// TODO Auto-generated method stub
		return yearMasterRepository.findAll();
	}

	@Override
	public YearMaster getDataById(long id) {
		// TODO Auto-generated method stub
		return yearMasterRepository.findById(id).orElse(null);
	}

	@Override
	public YearMaster postData(YearMaster yearMaster) {
		// TODO Auto-generated method stub
		return yearMasterRepository.save(yearMaster);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		yearMasterRepository.deleteById(id);
	}

}
