package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.BankPayment;
import com.api.repository.account.BankPaymentRepository;
import com.api.service.account.BankPaymentService;

@Service
public class BankPaymentServiceImpl implements BankPaymentService{
	
	@Autowired
	private BankPaymentRepository bankPaymentRepository;

	@Override
	public List<BankPayment> getAllData() {
		// TODO Auto-generated method stub
		return bankPaymentRepository.findAll();
	}

	@Override
	public BankPayment getById(long id) {
		// TODO Auto-generated method stub
		return bankPaymentRepository.findById(id).orElse(null);
	}

	@Override
	public BankPayment postData(BankPayment bankPayment) {
		// TODO Auto-generated method stub
		return bankPaymentRepository.save(bankPayment);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		bankPaymentRepository.deleteById(id);
	}

	@Override
	public List<BankPayment> getbySchoolUdise(School school) {
		// TODO Auto-generated method stub
		return bankPaymentRepository.findByschoolUdise(school);
	}

}
