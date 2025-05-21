package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.BankReceipt;
import com.api.repository.account.BankReceiptRepository;
import com.api.service.account.BankReceiptService;

@Service
public class BankReceiptServiceImpl implements BankReceiptService{

	
	@Autowired
	private BankReceiptRepository bankReceiptRepository;
	
	@Override
	public List<BankReceipt> getAllData() {
		// TODO Auto-generated method stub
		return bankReceiptRepository.findAll();
	}

	@Override
	public BankReceipt getById(long id) {
		// TODO Auto-generated method stub
		return bankReceiptRepository.findById(id).orElse(null);
	}

	@Override
	public BankReceipt postData(BankReceipt bankReceipt) {
		// TODO Auto-generated method stub
		return bankReceiptRepository.save(bankReceipt);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		bankReceiptRepository.deleteById(id);
	}

	@Override
	public List<BankReceipt> getbySchoolUdise(School school) {
		// TODO Auto-generated method stub
		return bankReceiptRepository.findByschoolUdise(school);
	}

}
