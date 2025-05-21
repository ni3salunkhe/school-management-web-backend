package com.api.serviceImpl.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.EntryType;
import com.api.repository.account.EntryTypeRepository;
import com.api.service.SchoolService;
import com.api.service.account.EntryTypeService;


@Service
public class EntryTypeServiceImpl implements EntryTypeService{
	
	@Autowired 
	private EntryTypeRepository entryTypeRepository;

	@Autowired 
	private SchoolService schoolService;
	
	@Override
	public EntryType getById(long id) {
		// TODO Auto-generated method stub
		return entryTypeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("GeneralLedger not found with entryNo: " + id));
	}
	
	@Override
	public EntryType post(EntryType entryType) {
		// TODO Auto-generated method stub
		return entryTypeRepository.save(entryType);
	}

	@Override
	public List<EntryType> getdata() {
		// TODO Auto-generated method stub
		return entryTypeRepository.findAll();
	}

	@Override
	public List<EntryType> getdatabyudiseno(long udiseNo) {
		// TODO Auto-generated method stub
		School school= schoolService.getbyid(udiseNo);
		return entryTypeRepository.findBySchoolUdiseNo(school);
	}

	@Override
	public void deletbyid(long id) {
		// TODO Auto-generated method stub
		entryTypeRepository.deleteById(id);
	}

		
}
