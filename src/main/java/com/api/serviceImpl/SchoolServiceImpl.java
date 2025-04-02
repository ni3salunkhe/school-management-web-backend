package com.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.repository.SchoolRepository;
import com.api.service.SchoolService;

@Service
public class SchoolServiceImpl implements SchoolService{

	@Autowired
	private SchoolRepository schoolRepository;
	
	@Override
	public School post(School school) {
		// TODO Auto-generated method stub
		return schoolRepository.save(school);
	}

	@Override
	public List<School> getdata() {
		// TODO Auto-generated method stub
		return schoolRepository.findAll();
	}

	@Override
	public School getbyid(long id) {
		// TODO Auto-generated method stub
		return schoolRepository.findById(id).orElse(null);
	}

	@Override
	public void deletedata(long id) {
		// TODO Auto-generated method stub
		schoolRepository.deleteById(id);
		
	}

}
