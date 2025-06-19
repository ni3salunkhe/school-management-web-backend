package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.entity.account.ExpensesVouchar;
import com.api.repository.account.ExpensesVoucharRepository;
import com.api.service.account.ExpensesVoucharService;

public class ExpensesVoucharServiceImpl implements ExpensesVoucharService {

	@Autowired
	private ExpensesVoucharRepository expensesVoucharRepository;
	
	@Override
	public List<ExpensesVouchar> getAllData() {
		// TODO Auto-generated method stub
		return expensesVoucharRepository.findAll();
	}

	@Override
	public ExpensesVouchar getById(long id) {
		// TODO Auto-generated method stub
		return expensesVoucharRepository.findById(id).orElse(null);
	}

	@Override
	public ExpensesVouchar postData(ExpensesVouchar expensesVouchar) {
		// TODO Auto-generated method stub
		return expensesVoucharRepository.save(expensesVouchar);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		expensesVoucharRepository.deleteById(id);
	}

	@Override
	public List<ExpensesVouchar> getDataByUdiseNo(long UdiseNo) {
		// TODO Auto-generated method stub
		return expensesVoucharRepository.findBySchoolUdiseUdiseNo(UdiseNo);
	}

}
