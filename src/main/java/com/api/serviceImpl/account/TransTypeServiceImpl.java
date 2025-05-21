package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.TransType;
import com.api.repository.account.TransTypeRepository;
import com.api.service.SchoolService;
import com.api.service.account.TransTypeService;

@Service
public class TransTypeServiceImpl implements TransTypeService{

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private TransTypeRepository transTypeRepository;
	
	@Override
	public TransType post(TransType transType) {
		// TODO Auto-generated method stub
		return transTypeRepository.save(transType);
	}

	@Override
	public List<TransType> getbyudiseno(long udiseno) {
		// TODO Auto-generated method stub
		School school = schoolService.getbyid(udiseno);
		return transTypeRepository.findBySchoolUdiseNo(school);
	}

	@Override
	public void deletebyid(int id) {
		// TODO Auto-generated method stub
		transTypeRepository.deleteById((long) id);
	}

	@Override
	public List<TransType> getdata() {
		// TODO Auto-generated method stub
		return transTypeRepository.findAll();
	}

}
