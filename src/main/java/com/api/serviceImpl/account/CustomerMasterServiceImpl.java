package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.CustomerMaster;
import com.api.repository.account.CustomerMasterRepository;
import com.api.service.account.CustomerMasterService;

@Service
public class CustomerMasterServiceImpl implements CustomerMasterService {

	@Autowired
	private CustomerMasterRepository customerMasterRepository;

	@Override
	public List<CustomerMaster> getAllMasters() {
		// TODO Auto-generated method stub
		return customerMasterRepository.findAll();
	}

	@Override
	public CustomerMaster getById(long id) {
		// TODO Auto-generated method stub
		return customerMasterRepository.findById(id).orElse(null);
	}

	@Override
	public CustomerMaster postData(CustomerMaster customerMaster) {
		// TODO Auto-generated method stub
		return customerMasterRepository.save(customerMaster);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		customerMasterRepository.deleteById(id);
	}

	@Override
	public List<CustomerMaster> getByUdise(School school) {
		// TODO Auto-generated method stub
		return customerMasterRepository.findBySchoolUdise(school);
	}

}
