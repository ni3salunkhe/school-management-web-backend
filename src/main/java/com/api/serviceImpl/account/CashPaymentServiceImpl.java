package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.CashPayment;
import com.api.repository.account.CashPaymentRepository;
import com.api.service.SchoolService;
import com.api.service.account.CashPaymentService;

@Service
public class CashPaymentServiceImpl implements CashPaymentService{

	@Autowired
	private CashPaymentRepository cashPaymentRepository;
	
	@Autowired
	private SchoolService schoolService;
	
	@Override
	public List<CashPayment> getAllData() {
		// TODO Auto-generated method stub
		return cashPaymentRepository.findAll();
	}

	@Override
	public CashPayment getById(long id) {
		// TODO Auto-generated method stub
		return cashPaymentRepository.findById(id).orElse(null);
	}

	@Override
	public CashPayment postData(CashPayment cashPayment) {
		// TODO Auto-generated method stub
		return cashPaymentRepository.save(cashPayment);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		cashPaymentRepository.deleteById(id);
	}

	@Override
	public List<CashPayment> getbySchoolUdise(long udise) {
		// TODO Auto-generated method stub
		School school = schoolService.getbyid(udise);
		return cashPaymentRepository.findByschoolUdise(school);
	}

}
