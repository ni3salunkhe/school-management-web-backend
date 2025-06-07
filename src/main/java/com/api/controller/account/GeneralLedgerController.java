package com.api.controller.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.account.GeneralLedgerDto;
import com.api.entity.account.CustomerMaster;
import com.api.entity.account.GeneralLedger;
import com.api.entity.account.HeadMaster;
import com.api.entity.account.SubHeadMaster;
import com.api.repository.account.CustomerMasterRepository;
import com.api.service.SchoolService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.EntryTypeService;
import com.api.service.account.GeneralLedgerService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.SubHeadMasterService;
import com.api.serviceImpl.account.OpeningBalServiceImpl;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/generalledger")
public class GeneralLedgerController {

	private static final Logger log = LoggerFactory.getLogger(GeneralLedgerController.class);

	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	private HeadMasterService headMasterService;
	
	@Autowired
	private SubHeadMasterService subHeadMasterService;
	
	@Autowired
	private CustomerMasterService customerMasterService;
	
	@Autowired
	private EntryTypeService entryTypeService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private CustomerMasterRepository customerMasterRepository;	
	@PostMapping("/")
	public ResponseEntity<GeneralLedger> savealldata(@RequestBody GeneralLedgerDto generalLedgerDto){
		GeneralLedger generalLedger = new GeneralLedger();
		
		generalLedger.setEntryNo(generalLedgerDto.getEntryNo());
		generalLedger.setEntryType(generalLedgerDto.getEntryType());
		generalLedger.setBillno(generalLedgerDto.getBillno());
		generalLedger.setCrAmt(generalLedgerDto.getCr_Amt());
		generalLedger.setCustId(customerMasterService.getById(generalLedgerDto.getCustid()));
		generalLedger.setDaybookname(generalLedgerDto.getDaybookname());
		generalLedger.setDrAmt(generalLedgerDto.getDr_Amt());
		generalLedger.setEntrydate(generalLedgerDto.getEntrydate());
		generalLedger.setEntrynochar(generalLedgerDto.getEntrynochar());
		generalLedger.setHeadId(headMasterService.getById(generalLedgerDto.getHeadid()));
		generalLedger.setMaxno(generalLedger.getMaxno());
		generalLedger.setNarr(generalLedgerDto.getNarr());
		generalLedger.setSaledup(generalLedgerDto.getSalepaydup());
		generalLedger.setShopId(schoolService.getbyid(generalLedgerDto.getUdiseNo()));
		generalLedger.setSubhead(subHeadMasterService.getById(generalLedgerDto.getSubhead()));
		generalLedger.setYear(generalLedgerDto.getYear());
		generalLedger.setSalepaydate(generalLedgerDto.getSalepaydate());
		
		GeneralLedger savedData=generalLedgerService.post(generalLedger);
		return new ResponseEntity<GeneralLedger>(savedData,HttpStatus.OK);
		
	}
	
	@GetMapping("/{udiseNo}")
	public ResponseEntity<List<GeneralLedger>> getbyudisedata(@PathVariable long udiseNo )
	{
		List<GeneralLedger> generalLedgers=generalLedgerService.getbyudise(udiseNo);
		return new ResponseEntity<List<GeneralLedger>>(generalLedgers,HttpStatus.OK);
		
	}
	
	@GetMapping("/balances/{id}")
	public ResponseEntity<List<GeneralLedger>> getbyid(@PathVariable long id){
		
		List<GeneralLedger> generalLedgers=generalLedgerService.getbysubhead(id);
		return new ResponseEntity<List<GeneralLedger>>(generalLedgers, HttpStatus.OK);
		
	}
	
	@GetMapping("/ledger/balance/{subheadId}")
	public ResponseEntity<BigDecimal> getBalanceBySubhead(@PathVariable Long subheadId) {
	    BigDecimal balance = generalLedgerService.getBalanceBySubhead(subheadId);
	    return ResponseEntity.ok(balance);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<GeneralLedger>> getall(){
		
		List<GeneralLedger> generalLedger = generalLedgerService.getdata();
		return new ResponseEntity<List<GeneralLedger>>(generalLedger, HttpStatus.OK);
	}

	@PostMapping("/bulk")
	public ResponseEntity<?> saveBulkOpeningBalance(@RequestBody List<GeneralLedgerDto> dtoList) {
	    List<GeneralLedger> savedList = new ArrayList<>();

	    for (GeneralLedgerDto dto : dtoList) {
	        GeneralLedger ledger = new GeneralLedger();

	        if (dto.getCr_Amt() != null) {
	            ledger.setCrAmt(dto.getCr_Amt());
	        } else {
	            ledger.setCrAmt(0.0); // or leave unset if nullable
	        }

	        if (dto.getDr_Amt() != null) {
	            ledger.setDrAmt(dto.getDr_Amt());
	        } else {
	            ledger.setDrAmt(0.0); // or leave unset if nullable
	        }
	        
//	        ledger.setAmount(dto.getAmt());
	        ledger.setEntryType(dto.getEntryType());
//	        ledger.setYear(Integer.parseInt(dto.getYear()));
	        ledger.setNarr(dto.getNarr());

	        // Related Entity Mapping
	        HeadMaster head = headMasterService.getById(dto.getHeadid());
	        SubHeadMaster subHead = subHeadMasterService.getById(dto.getSubhead());
	        ledger.setHeadId(head);
	        ledger.setSubhead(subHead);

	        // Optional Customer
	        CustomerMaster customer = customerMasterRepository.findById((long) dto.getSubhead()).orElse(null);
	         // handle null downstream
		        if (customer == null) {
		            log.warn("Customer not found: {}", dto.getSubhead());
		             // maybe skip or handle with fallback values
		        }
		        
		        ledger.setCustId(customer);

	        // Save and collect
	        savedList.add(generalLedgerService.post(ledger));
	    }

	    return ResponseEntity.ok(savedList);
	}


}
