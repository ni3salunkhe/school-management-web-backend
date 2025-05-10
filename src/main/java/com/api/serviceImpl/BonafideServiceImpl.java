package com.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Bonafide;
import com.api.repository.BonafideRepository;
import com.api.service.BonafideService;

@Service
public class BonafideServiceImpl implements BonafideService{

	@Autowired
	private BonafideRepository bonafideRepository;
	
	@Override
	public List<Bonafide> getAllData() {
		// TODO Auto-generated method stub
		return bonafideRepository.findAll();
	}

	@Override
	public Bonafide getbyid(long id) {
		// TODO Auto-generated method stub
		return bonafideRepository.findById(id).orElse(null);
	}

	@Override
	public Bonafide postData(Bonafide bonafide) {
		// TODO Auto-generated method stub
		return bonafideRepository.save(bonafide);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		bonafideRepository.deleteById(id);
	}

}
