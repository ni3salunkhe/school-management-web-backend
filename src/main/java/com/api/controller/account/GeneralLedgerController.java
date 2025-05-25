package com.api.controller.account;

import java.util.List;

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
import com.api.entity.account.GeneralLedger;
import com.api.service.SchoolService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.EntryTypeService;
import com.api.service.account.GeneralLedgerService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.SubHeadMasterService;

@RestController
@RequestMapping("/generalledger")
public class GeneralLedgerController {

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
	
	@PostMapping
	public ResponseEntity<GeneralLedger> savealldata(@RequestBody GeneralLedgerDto generalLedgerDto){
		GeneralLedger generalLedger = new GeneralLedger();
		
		generalLedger.setEntryType(entryTypeService.getById(generalLedgerDto.getEntryType()));
		generalLedger.setBillno(generalLedgerDto.getBillno());
		generalLedger.setCr_Amt(generalLedgerDto.getCr_Amt());
		generalLedger.setCustId(customerMasterService.getById(generalLedgerDto.getCustid()));
		generalLedger.setDaybookname(generalLedgerDto.getDaybookname());
		generalLedger.setDr_Amt(generalLedgerDto.getDr_Amt());
		generalLedger.setEntrydate(generalLedgerDto.getEntrydate());
		generalLedger.setEntrynochar(generalLedgerDto.getEntrynochar());
		generalLedger.setHead_id(headMasterService.getById(generalLedgerDto.getHeadid()));
		generalLedger.setMaxno(generalLedger.getMaxno());
		generalLedger.setNarr(generalLedgerDto.getNarr());
		generalLedger.setSaledup(generalLedgerDto.getSalepaydup());
		generalLedger.setShopId(schoolService.getbyid(generalLedgerDto.getUdiseNo()));
		generalLedger.setSubhead(subHeadMasterService.getById(generalLedgerDto.getSubhead()));
		generalLedger.setYear(generalLedgerDto.getYear());
		generalLedger.setSalepaydate(generalLedgerDto.getSalepaydate());
		
		GeneralLedger savedData=generalLedgerService.post(generalLedger);
		return new ResponseEntity<GeneralLedger>(generalLedger,HttpStatus.OK);
		
	}
	
	@GetMapping("/{udiseNo}")
	public ResponseEntity<List<GeneralLedger>> getbyudisedata(@PathVariable long udiseNo )
	{
		List<GeneralLedger> generalLedgers=generalLedgerService.getbyudise(udiseNo);
		return new ResponseEntity<List<GeneralLedger>>(generalLedgers,HttpStatus.OK);
		
	}
}
