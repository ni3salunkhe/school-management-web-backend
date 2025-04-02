package com.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Division;
import com.api.repository.DivisionRepository;
import com.api.service.DivisionService;

@Service
public class DivisionServiceImpl implements DivisionService {

	@Autowired
	private DivisionRepository divisionRepository;
	
	@Override
	public Division post(Division division) {
		// TODO Auto-generated method stub
		return divisionRepository.save(division);
	}

	@Override
	public List<Division> getdata() {
		// TODO Auto-generated method stub
		return divisionRepository.findAll();
	}

	@Override
	public Division getbyid(long id) {
		// TODO Auto-generated method stub
		return divisionRepository.findById(id).orElse(null);
	}

	@Override
	public void deletedata(long id) {
		// TODO Auto-generated method stub
		divisionRepository.deleteById(id);
	}

}
