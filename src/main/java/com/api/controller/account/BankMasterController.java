package com.api.controller.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.account.BankMasterDto;
import com.api.entity.School;
import com.api.entity.account.AccountType;
import com.api.entity.account.BankMaster;
import com.api.entity.account.CustomerMaster;
import com.api.entity.account.SubHeadMaster;
import com.api.service.SchoolService;
import com.api.service.account.AccountTypeService;
import com.api.service.account.BankMasterService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.SubHeadMasterService;

@RestController
@RequestMapping("/bank")
public class BankMasterController {

	@Autowired
	private BankMasterService bankMasterService;

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private AccountTypeService accountTypeService;
	
	@Autowired
	private HeadMasterService headMasterService;
	
	@Autowired
	private CustomerMasterService customerMasterService;
	
	@Autowired
	private SubHeadMasterService subHeadMasterService;

	@PostMapping("/")
	public ResponseEntity<BankMaster> savebankdata(@RequestBody BankMasterDto bankDto) {

		long id=subHeadMasterService.getNextLedgerId();
		
		CustomerMaster customerMaster=new CustomerMaster();
		customerMaster.setCustName(bankDto.getBankname());
		customerMaster.setHeadId(headMasterService.getById(bankDto.getHeadId()));
//		customerMaster.setSubheadId(customerMaster.getSubheadId());
		customerMaster.setSchoolUdise(schoolService.getbyid(bankDto.getUdiseNo()));
		
		CustomerMaster saveCustomerMaster=customerMasterService.postData(customerMaster);
		
		SubHeadMaster subHeadMaster=new SubHeadMaster();
		subHeadMaster.setHeadId(headMasterService.getById(bankDto.getHeadId()));
		subHeadMaster.setSchoolUdise(schoolService.getbyid(bankDto.getUdiseNo()));
		subHeadMaster.setSubheadId(id);
		subHeadMaster.setSubheadName(bankDto.getBankname());
		
		SubHeadMaster saveSubHeadMaster= subHeadMasterService.postData(subHeadMaster);
		
		saveCustomerMaster.setSubheadId(saveSubHeadMaster);
		
		BankMaster bankMaster = new BankMaster();
		School school = schoolService.getbyid(bankDto.getUdiseNo());
		AccountType accountType= accountTypeService.getbyid(bankDto.getAccounttype());
		if (school != null) {
			bankMaster.setBankname(bankDto.getBankname());
			bankMaster.setIfsccode(bankDto.getIfsccode());
			bankMaster.setBranch(bankDto.getBranch());
			bankMaster.setAddress(bankDto.getAddress());
			bankMaster.setSchoolUdiseNo(school);
			bankMaster.setAccounttype(accountType);
			bankMaster.setAccountno(bankDto.getAccountno());
			bankMaster.setHeadId(headMasterService.getById(bankDto.getHeadId()));
			bankMaster.setSubHeadId(saveSubHeadMaster);
			bankMaster.setCustId(saveCustomerMaster);
			BankMaster saveBankMaster = bankMasterService.post(bankMaster);

			return new ResponseEntity<BankMaster>(saveBankMaster, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<BankMaster>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<BankMaster> putbyid(@PathVariable long id, @RequestBody BankMasterDto bankDto) {

		BankMaster bankMaster = bankMasterService.getbyid(id);
		AccountType accountType= accountTypeService.getbyid(bankDto.getAccounttype());
		if (bankMaster != null) {

			bankMaster.setBankname(bankDto.getBankname());
			bankMaster.setBranch(bankDto.getBranch());
			bankMaster.setAddress(bankDto.getAddress());
			bankMaster.setIfsccode(bankDto.getIfsccode());
			bankMaster.setSchoolUdiseNo(schoolService.getbyid(bankDto.getUdiseNo()));
			bankMaster.setAccounttype(accountType);
			bankMaster.setAccountno(bankDto.getAccountno());
			bankMaster.setHeadId(headMasterService.getById(bankDto.getHeadId()));
			BankMaster saveBankMaster = bankMasterService.post(bankMaster);

			return new ResponseEntity<BankMaster>(saveBankMaster, HttpStatus.OK);

		} else {

			return new ResponseEntity<BankMaster>(HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping("/byudiseno/{udiseNo}")
	public ResponseEntity<List<BankMaster>> getbanksbyudise(@PathVariable long udiseNo) {
		List<BankMaster> bankMaster = bankMasterService.getbyudiseno(udiseNo);
		return new ResponseEntity<>(bankMaster, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<BankMaster>> getalldata() {

		List<BankMaster> bankMaster = bankMasterService.getdata();

		return new ResponseEntity<List<BankMaster>>(bankMaster, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletebyid(BankMasterDto bankDto) {
		bankMasterService.deletebyid(bankDto.getId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
