package com.api.serviceImpl.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.account.OpeningBalDto;
import com.api.entity.account.CustomerMaster;
import com.api.entity.account.HeadMaster;
import com.api.entity.account.OpeningBal;
import com.api.entity.account.SubHeadMaster;
import com.api.repository.account.CustomerMasterRepository;
import com.api.repository.account.HeadMasterRepository;
import com.api.repository.account.OpeningBalRepository;
import com.api.repository.account.SubHeadMasterRepository;
import com.api.service.SchoolService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.OpeningBalService;

@Service
public class OpeningBalServiceImpl implements OpeningBalService{

	@Autowired
	private OpeningBalRepository openingBalRepository;
	
	@Autowired
	private CustomerMasterService customerMasterService;
	
	@Autowired
	private CustomerMasterRepository customerMasterRepository;
	
	@Autowired
	private HeadMasterRepository headMasterRepository;
	
	@Autowired
	private SubHeadMasterRepository subHeadMasterRepository;
	
	@Autowired
	private SchoolService schoolService;
	
	private static final Logger log = LoggerFactory.getLogger(OpeningBalServiceImpl.class);

	@Override
	public List<OpeningBal> getAllData() {
		// TODO Auto-generated method stub
		return openingBalRepository.findAll();
	}

	@Override
	public OpeningBal getByIdData(long id) {
		// TODO Auto-generated method stub
		return openingBalRepository.findById(id).orElse(null);
	}

	@Override
	public OpeningBal postData(OpeningBal openingBal) {
		// TODO Auto-generated method stub
		return openingBalRepository.save(openingBal);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		openingBalRepository.deleteById(id);
	}

	@Override
	public void saveOpeningBalances(OpeningBalDto request) {
        String financialYear = request.getFinancialYear();
        
        System.out.println(request);

        for (OpeningBalDto.BalanceEntry entry : request.getBalances()) {
            OpeningBal openingBal = new OpeningBal();

            // Lookup and assign Customer
            CustomerMaster customer = customerMasterRepository.findById(entry.getSubHeadId()).orElse(null);
         // handle null downstream
	        if (customer == null) {
	            log.warn("Customer not found: {}", entry.getSubHeadId());
	             // maybe skip or handle with fallback values
	        }
            openingBal.setCustId(customer);
            openingBal.setSchoolUdise(schoolService.getbyid(entry.getUdiseNo()));

            // Lookup and assign Head
            HeadMaster head = headMasterRepository.findById(entry.getHeadId())
                    .orElseThrow(() -> new RuntimeException("Head not found: " + entry.getHeadId()));
            openingBal.setHeadId(head);

            // Lookup and assign SubHead
            SubHeadMaster subHead = subHeadMasterRepository.findById(entry.getSubHeadId())
                    .orElseThrow(() -> new RuntimeException("Subhead not found: " + entry.getSubHeadId()));
            openingBal.setSubHeadId(subHead);

            // Assign Debit or Credit
            double amount = entry.getAmount() != null ? entry.getAmount() : 0.0;
            if ("Credit".equalsIgnoreCase(entry.getBalanceType())) {
                openingBal.setCrAmt(amount);
                openingBal.setDrAmt(0.0);
            } else {
                openingBal.setDrAmt(amount);
                openingBal.setCrAmt(0.0);
            }

            openingBal.setAmount(amount);
            openingBal.setYear(request.getFinancialYear());

            // Save entry
            openingBalRepository.save(openingBal);
        }
    }
	
	@Override
	public Map<String, Double> getSumCrDr() {
	    Object result = openingBalRepository.getTotalCreditAndDebit();
	    
	    Object[] row = (Object[]) result;

	    Double totalDr = row[0] != null ? ((Number) row[0]).doubleValue() : 0.0;
	    Double totalCr = row[1] != null ? ((Number) row[1]).doubleValue() : 0.0;

	    Map<String, Double> totals = new HashMap<>();
	    totals.put("totalDr", totalDr);
	    totals.put("totalCr", totalCr);

	    return totals;
	}

}
