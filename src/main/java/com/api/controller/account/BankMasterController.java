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
import org.springframework.web.service.annotation.DeleteExchange;

import com.api.dto.account.BankMasterDto;
import com.api.entity.School;
import com.api.entity.account.BankMaster;
import com.api.service.SchoolService;
import com.api.service.account.BankMasterService;

@RestController
@RequestMapping("/bank")
public class BankMasterController {

	@Autowired
	private BankMasterService bankMasterService;

	@Autowired
	private SchoolService schoolService;

	@PostMapping("/")
	public ResponseEntity<BankMaster> savebankdata(@RequestBody BankMasterDto bankDto) {

		BankMaster bankMaster = new BankMaster();
		School school = schoolService.getbyid(bankDto.getUdiseNo());

		if (school != null) {
			bankMaster.setBankname(bankDto.getBankname());
			bankMaster.setIfsccode(bankDto.getIfsccode());
			bankMaster.setBranch(bankDto.getBranch());
			bankMaster.setAddress(bankDto.getAddress());
			bankMaster.setSchoolUdiseNo(school);

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

		if (bankMaster != null) {

			bankMaster.setBankname(bankDto.getBankname());
			bankMaster.setBranch(bankDto.getBankname());
			bankMaster.setAddress(bankDto.getAddress());
			bankMaster.setIfsccode(bankDto.getIfsccode());
			bankMaster.setSchoolUdiseNo(schoolService.getbyid(bankDto.getUdiseNo()));
			BankMaster saveBankMaster = bankMasterService.post(bankMaster);

			return new ResponseEntity<BankMaster>(saveBankMaster, HttpStatus.OK);

		} else {

			return new ResponseEntity<BankMaster>(HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping("/{udiseNo}")
	public ResponseEntity<List<BankMaster>> getbanksbyudise(@PathVariable long udiseNo) {
		List<BankMaster> bankMaster = bankMasterService.getbyudiseno(udiseNo);
		return new ResponseEntity<>(bankMaster, HttpStatus.OK);
	}

	@GetMapping
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
