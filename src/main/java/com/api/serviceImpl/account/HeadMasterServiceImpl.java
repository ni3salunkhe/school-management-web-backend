package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.HeadMaster;
import com.api.repository.account.HeadMasterRepository;
import com.api.service.SchoolService;
import com.api.service.account.HeadMasterService;

@Service
public class HeadMasterServiceImpl implements HeadMasterService{

	@Autowired
	private HeadMasterRepository headMasterRepository;
	
	@Autowired
	private SchoolService schoolService;
	
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

	@Override
	public List<HeadMaster> getByUdiseNo(long udiseNo) {
		// TODO Auto-generated method stub
		School school = schoolService.getbyid(udiseNo);
		return headMasterRepository.findBySchoolUdise(school);
	}

	@Override
	public HeadMaster getByHeadName(String headName) {
		// TODO Auto-generated method stub
		return headMasterRepository.findByHeadName(headName);
	}

	@Override
	public List<HeadMaster> getByBookSideName(String BookSideName) {
		// TODO Auto-generated method stub
		return headMasterRepository.findByBookSideMasterBooksideName(BookSideName);
	}

}
