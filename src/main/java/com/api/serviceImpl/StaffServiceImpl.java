package com.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Staff;
import com.api.repository.StaffRepository;
import com.api.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffRepository staffRepository;
	
	@Override
	public Staff post(Staff staff) {
		// TODO Auto-generated method stub
		return staffRepository.save(staff);
	}

	@Override
	public List<Staff> getdata() {
		// TODO Auto-generated method stub
		return staffRepository.findAll();
	}

	@Override
	public Staff getbyid(long id) {
		// TODO Auto-generated method stub
		return staffRepository.findById(id).orElse(null);
	}

	@Override
	public void deletedata(long id) {
		// TODO Auto-generated method stub
		staffRepository.deleteById(id);
	}

}
