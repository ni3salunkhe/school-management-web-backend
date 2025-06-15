package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.SubHeadMaster;
import com.api.repository.account.SubHeadMasterRepository;
import com.api.service.SchoolService;
import com.api.service.account.SubHeadMasterService;

@Service
public class SubHeadMasterServiceImpl implements SubHeadMasterService{

	
	@Autowired
	private SubHeadMasterRepository subHeadMasterRepository;
	
	@Autowired
	private SchoolService schoolService;
	
	@Override
	public List<SubHeadMaster> getAllData() {
		// TODO Auto-generated method stub
		return subHeadMasterRepository.findAll();
	}

	@Override
	public SubHeadMaster getById(long id) {
		// TODO Auto-generated method stub
		return subHeadMasterRepository.findById(id).orElse(null);
	}

	@Override
	public SubHeadMaster postData(SubHeadMaster subHeadMaster) {
		// TODO Auto-generated method stub
		return subHeadMasterRepository.save(subHeadMaster);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		subHeadMasterRepository.deleteById(id);
	}

	@Override
	public List<SubHeadMaster> getbyudiseno(long udiseNo) {
		// TODO Auto-generated method stub
		School school = schoolService.getbyid(udiseNo);
		return subHeadMasterRepository.findBySchoolUdise(school);
	}
	
	public Long getNextLedgerId() {
	    return subHeadMasterRepository.findMaxId() + 1;
	}

	@Override
	public List<SubHeadMaster> getByUdiseAndBookSideName(String booksideName, long udise) {
		// TODO Auto-generated method stub
		return subHeadMasterRepository.findByBookSideNameAndSchool(booksideName, udise);
	}


}
