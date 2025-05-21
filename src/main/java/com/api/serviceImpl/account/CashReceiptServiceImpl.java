package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.CashReceipt;
import com.api.repository.account.CashReceiptRepository;
import com.api.service.account.CashReceiptService;

@Service
public class CashReceiptServiceImpl implements CashReceiptService{

	@Autowired
	private CashReceiptRepository cashReceiptRepository;
	
	@Override
	public List<CashReceipt> getAllData() {
		// TODO Auto-generated method stub
		return cashReceiptRepository.findAll();
	}

	@Override
	public CashReceipt getById(long id) {
		// TODO Auto-generated method stub
		return cashReceiptRepository.findById(id).orElse(null);
	}

	@Override
	public CashReceipt postData(CashReceipt cashReceipt) {
		// TODO Auto-generated method stub
		return cashReceiptRepository.save(cashReceipt);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		cashReceiptRepository.deleteById(id);
	}

	@Override
	public List<CashReceipt> getbySchoolUdise(School school) {
		// TODO Auto-generated method stub
		return cashReceiptRepository.findByschoolUdise(school);
	}

}
