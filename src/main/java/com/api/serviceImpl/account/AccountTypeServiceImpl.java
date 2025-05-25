package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.AccountType;
import com.api.repository.account.AccountTypeRepository;
import com.api.service.SchoolService;
import com.api.service.account.AccountTypeService;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {
	
	@Autowired
	private AccountTypeRepository accountTypeRepository;
	
	@Autowired
	private SchoolService schoolService;

	@Override
	public AccountType post(AccountType accountType) {
		// TODO Auto-generated method stub
		return accountTypeRepository.save(accountType);
	}

	@Override
	public List<AccountType> getbyudiseno(long udiseNo) {
		// TODO Auto-generated method stub
		School school = schoolService.getbyid(udiseNo);
		return accountTypeRepository.findBySchoolUdiseNo(school);
	}

	@Override
	public AccountType getbyid(int id) {
		// TODO Auto-generated method stub
		return accountTypeRepository.findById((long) id).orElse(null);
	}

	
}
