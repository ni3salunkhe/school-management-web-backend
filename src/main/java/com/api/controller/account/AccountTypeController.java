package com.api.controller.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.account.AccountTypeDto;
import com.api.entity.School;
import com.api.entity.account.AccountType;
import com.api.service.SchoolService;
import com.api.service.account.AccountTypeService;

@RestController
@RequestMapping("/accounttype")
public class AccountTypeController {

	@Autowired
	private AccountTypeService accountTypeService;

	@Autowired
	private SchoolService schoolService;

	@PostMapping("/")
	public ResponseEntity<AccountType> savealldata(@RequestBody AccountTypeDto accDto) {
		AccountType accountType = new AccountType();

		School school = schoolService.getbyid(accDto.getUdiseNo());
		if (school != null) {
			accountType.setSchoolUdiseNo(school);
			accountType.setName(accDto.getName());

			AccountType savedData = accountTypeService.post(accountType);

			return new ResponseEntity<AccountType>(savedData, HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountType>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<AccountType> putbyid(@PathVariable int id, @RequestBody AccountTypeDto accDto){
		AccountType accountType=accountTypeService.getbyid(id);
		if(accountType != null) {
			accountType.setName(accDto.getName());
			AccountType savedData = accountTypeService.post(accountType);

			return new ResponseEntity<AccountType>(savedData, HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountType>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/byudiseno/{udiseNo}")
	public ResponseEntity<List<AccountType>> getallbyudise(@PathVariable long udiseNo) {

		List<AccountType> accountType = accountTypeService.getbyudiseno(udiseNo);
		return new ResponseEntity<List<AccountType>>(accountType, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountType> getdatabyid(@PathVariable int id) {
		AccountType accountType = accountTypeService.getbyid(id);
		return new ResponseEntity<AccountType>(accountType, HttpStatus.OK);
	}
}
