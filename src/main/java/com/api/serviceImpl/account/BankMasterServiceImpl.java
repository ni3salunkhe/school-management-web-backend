package com.api.serviceImpl.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.BankMaster;
import com.api.repository.account.BankMasterRepository;
import com.api.service.SchoolService;
import com.api.service.account.BankMasterService;

@Service
public class BankMasterServiceImpl implements BankMasterService {
	
	@Autowired
	private BankMasterRepository bankMasterRepository;
	
	@Autowired
	private SchoolService schoolService;

	@Override
	public BankMaster post(BankMaster bankMaster) {
		// TODO Auto-generated method stub
		return bankMasterRepository.save(bankMaster);
	}

	@Override
	public BankMaster getbyid(long id) {
		// TODO Auto-generated method stub
		return bankMasterRepository.findById(id).orElse(null);
	}

	@Override
	public List<BankMaster> getdata() {
		// TODO Auto-generated method stub
		return bankMasterRepository.findAll();
	}

	@Override
	public void deletebyid(long id) {
		// TODO Auto-generated method stub
		bankMasterRepository.deleteById(id);
	}

	@Override
	public List<BankMaster> getbyudiseno(long udiseNo) {
		// TODO Auto-generated method stub
		School school=schoolService.getbyid(udiseNo);
		return bankMasterRepository.findBySchoolUdiseNo(school);
	}

}
