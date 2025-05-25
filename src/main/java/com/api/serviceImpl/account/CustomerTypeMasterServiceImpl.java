package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.account.CustomerTypeMaster;
import com.api.repository.account.CustomerTypeMasterRepository;
import com.api.service.account.CustomerTypeMasterService;


@Service
public class CustomerTypeMasterServiceImpl implements CustomerTypeMasterService {

	@Autowired
	private CustomerTypeMasterRepository customerTypeMasterRepository;

	@Override
	public List<CustomerTypeMaster> allData() {
		// TODO Auto-generated method stub
		return customerTypeMasterRepository.findAll();
	}

	@Override
	public CustomerTypeMaster getById(long id) {
		// TODO Auto-generated method stub
		return customerTypeMasterRepository.findById(id).orElse(null);
	}

	@Override
	public CustomerTypeMaster postData(CustomerTypeMaster customerTypeMaster) {
		// TODO Auto-generated method stub
		return customerTypeMasterRepository.save(customerTypeMaster);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		customerTypeMasterRepository.deleteById(id);
	}

}
