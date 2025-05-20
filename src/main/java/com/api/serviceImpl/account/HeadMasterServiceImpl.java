package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.account.HeadMaster;
import com.api.repository.account.HeadMasterRepository;
import com.api.service.account.HeadMasterService;

@Service
public class HeadMasterServiceImpl implements HeadMasterService{

	@Autowired
	private HeadMasterRepository headMasterRepository;
	
	@Override
	public List<HeadMaster> getAllData() {
		// TODO Auto-generated method stub
		return headMasterRepository.findAll();
	}

	@Override
	public HeadMaster getById(long id) {
		// TODO Auto-generated method stub
		return headMasterRepository.findById(id).orElse(null);
	}

	@Override
	public HeadMaster postData(HeadMaster headMaster) {
		// TODO Auto-generated method stub
		return headMasterRepository.save(headMaster);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		headMasterRepository.deleteById(id);
	}

}
