package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.account.OpeningBal;
import com.api.repository.account.OpeningBalRepository;
import com.api.service.account.OpeningBalService;

@Service
public class OpeningBalServiceImpl implements OpeningBalService{

	@Autowired
	private OpeningBalRepository openingBalRepository;
	
	@Override
	public List<OpeningBal> getAllData() {
		// TODO Auto-generated method stub
		return openingBalRepository.findAll();
	}

	@Override
	public OpeningBal getByIdData(long id) {
		// TODO Auto-generated method stub
		return openingBalRepository.findById(id).orElse(null);
	}

	@Override
	public OpeningBal postData(OpeningBal openingBal) {
		// TODO Auto-generated method stub
		return openingBalRepository.save(openingBal);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		openingBalRepository.deleteById(id);
	}

}
