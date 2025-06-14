package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.account.JournalPayment;
import com.api.repository.account.JournalRepository;
import com.api.service.account.JournalPaymentService;

@Service
public class JournalPaymentServiceImpl implements JournalPaymentService{
	
	@Autowired
	private JournalRepository journalRepository;
	

	@Override
	public List<JournalPayment> getAllData() {
		// TODO Auto-generated method stub
		return journalRepository.findAll();
	}

	@Override
	public JournalPayment getById(long id) {
		// TODO Auto-generated method stub
		return journalRepository.findById(id).orElse(null);
	}

	@Override
	public JournalPayment postData(JournalPayment journalPayment) {
		// TODO Auto-generated method stub
		return journalRepository.save(journalPayment);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		journalRepository.deleteById(id);
	}

	@Override
	public List<JournalPayment> getDataByUdise(long udiseNo) {
		// TODO Auto-generated method stub
		return journalRepository.findBySchoolUdise_UdiseNo(udiseNo);
	}

	@Override
	public Long getTrasactionKey() {
		// TODO Auto-generated method stub
		return journalRepository.findMaxTransactionKey();
	}

}
