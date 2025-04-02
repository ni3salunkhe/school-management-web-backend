package com.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.StandardMaster;
import com.api.repository.StandardMasterRepository;
import com.api.service.StandardMasterService;

@Service
public class StandardMasterServiceImpl implements StandardMasterService{

	@Autowired
	private StandardMasterRepository standardMasterRepository;
	
	@Override
	public StandardMaster post(StandardMaster standardMaster) {
		// TODO Auto-generated method stub
		return standardMasterRepository.save(standardMaster);
	}

	@Override
	public List<Integer> getStandardsBySchool(long udiseNo) {
		// TODO Auto-generated method stub
		return standardMasterRepository.findStandardsBySchool(udiseNo)
                .stream()
                .map(StandardMaster::getStandard) // Extracting only standard values
                .collect(Collectors.toList());	}
	
}
