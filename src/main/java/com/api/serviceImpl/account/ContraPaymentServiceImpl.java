package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.ContraPayment;
import com.api.repository.ContraPaymentRepository;
import com.api.service.account.ContraPaymentService;

@Service
public class ContraPaymentServiceImpl implements ContraPaymentService {

	@Autowired
	private ContraPaymentRepository contraPaymentRepository;
	
	@Override
	public ContraPayment post(ContraPayment contraPayment) {
		// TODO Auto-generated method stub
		return contraPaymentRepository.save(contraPayment);
	}

	@Override
	public List<ContraPayment> getbyudise(School udiseNo) {
		// TODO Auto-generated method stub
		return contraPaymentRepository.findBySchoolUdise(udiseNo);
	}

	@Override
	public ContraPayment getbyid(long id) {
		// TODO Auto-generated method stub
		return contraPaymentRepository.findById(id).orElse(null);
	}

}
