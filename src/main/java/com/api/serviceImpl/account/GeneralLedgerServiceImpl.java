package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.GeneralLedger;
import com.api.repository.account.GeneralLedgerRepository;
import com.api.service.SchoolService;
import com.api.service.account.GeneralLedgerService;

@Service
public class GeneralLedgerServiceImpl implements GeneralLedgerService {
	
	@Autowired
	private GeneralLedgerRepository generalLedgerRepository;
	
	@Autowired
	private SchoolService schooService;

	@Override
	public GeneralLedger post(GeneralLedger generalLedger) {
		// TODO Auto-generated method stub
		return generalLedgerRepository.save(generalLedger);
	}

	@Override
	public List<GeneralLedger> getbyudise(long udiseNo) {
		// TODO Auto-generated method stub
		School school=schooService.getbyid(udiseNo);
		return generalLedgerRepository.findByShopId(school);
	}

	@Override
	public GeneralLedger getbyid(long entryNo) {
	    return generalLedgerRepository.findById(entryNo)
	        .orElseThrow(() -> new RuntimeException("GeneralLedger not found with entryNo: " + entryNo));
	}


	@Override
	public List<GeneralLedger> getdata() {
		// TODO Auto-generated method stub
		return generalLedgerRepository.findAll();
	}

}
