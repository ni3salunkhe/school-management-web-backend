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

import com.api.dto.account.BankReciptDto;
import com.api.entity.School;
import com.api.entity.account.BankReceipt;
import com.api.service.SchoolService;
import com.api.service.account.BankReceiptService;

@RestController
@RequestMapping("/bankreceipt")
public class BankReceiptController {

	@Autowired
	private BankReceiptService bankReceiptService;
	
	@Autowired
	private SchoolService schoolService;

	@GetMapping("/")
	public ResponseEntity<List<BankReceipt>> getbankReciptData() {
		List<BankReceipt> bankRecipt = bankReceiptService.getAllData();

		return new ResponseEntity<List<BankReceipt>>(bankRecipt, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<BankReceipt> getbankReciptDataById(@PathVariable long id) {
		BankReceipt bankRecipt = bankReceiptService.getById(id);

		return new ResponseEntity<BankReceipt>(bankRecipt, HttpStatus.OK);

	}

	@GetMapping("/getbyudise/{udise}")
	public ResponseEntity<List<BankReceipt>> getBankReceiptDataByUdise(@PathVariable long id) {

		School school = schoolService.getbyid(id);

		List<BankReceipt> bankReceipts = bankReceiptService.getbySchoolUdise(school);

		return new ResponseEntity<List<BankReceipt>>(bankReceipts, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<BankReceipt> saveBankReciptData(@RequestBody BankReciptDto bankReciptDto) {
		return null;
	}

	@PutMapping("/{id}")
	public ResponseEntity<BankReceipt> editBankReciptData(@RequestBody BankReciptDto bankReciptDto,
			@PathVariable long id) {
		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BankReceipt> deleteBankReciptData(@PathVariable long id) {
		bankReceiptService.deleteData(id);
		return new ResponseEntity<BankReceipt>(HttpStatus.OK);
	}
}
