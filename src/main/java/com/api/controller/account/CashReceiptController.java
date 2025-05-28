package com.api.controller.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.account.CashReceiptDto;
import com.api.entity.School;
import com.api.entity.account.CashPayment;
import com.api.entity.account.CashReceipt;
import com.api.service.SchoolService;
import com.api.service.account.CashReceiptService;
import com.api.service.account.CustomerMasterService;

@RestController
@RequestMapping("/cashreceipt")
public class CashReceiptController {

	@Autowired
	private CashReceiptService cashReceiptService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private CustomerMasterService customerMasterService;

	@GetMapping("/")
	public ResponseEntity<List<CashReceipt>> getAllCashReceiptData() {
		List<CashReceipt> cashReceipts = cashReceiptService.getAllData();

		return new ResponseEntity<List<CashReceipt>>(cashReceipts, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CashReceipt> getCashReceiptById(@PathVariable long id) {
		CashReceipt cashReceipt = cashReceiptService.getById(id);

		return new ResponseEntity<CashReceipt>(cashReceipt, HttpStatus.OK);
	}

	@GetMapping("/getbyudise/{udise}")
	public ResponseEntity<List<CashReceipt>> getCashReceiptDataByUdise(@PathVariable long id) {

		School school = schoolService.getbyid(id);

		List<CashReceipt> cashReceipts = cashReceiptService.getbySchoolUdise(school);

		return new ResponseEntity<List<CashReceipt>>(cashReceipts, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<CashPayment> saveCashReceiptData(@RequestBody CashReceiptDto cashReceiptDto) {
		
		CashReceipt cashReceipt=new CashReceipt();
		cashReceipt.setEntryDate(cashReceiptDto.getEntryDate());
		cashReceipt.setCustId(customerMasterService.getById(cashReceiptDto.getCustId()));
		cashReceipt.setTranType(cashReceiptDto.getTranType());
		cashReceipt.setAmount(cashReceiptDto.getAmount());
		
		return null;
	}

	@PostMapping("/{id}")
	public ResponseEntity<CashPayment> editCashReceiptData(@RequestBody CashReceiptDto cashReceiptDto,
			@PathVariable long id) {
		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCashReceiptData(@PathVariable long id) {
		cashReceiptService.deleteData(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
