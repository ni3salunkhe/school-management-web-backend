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

import com.api.dto.account.CashPaymentDto;
import com.api.entity.School;
import com.api.entity.account.CashPayment;
import com.api.service.SchoolService;
import com.api.service.account.CashPaymentService;

@RestController
@RequestMapping("/cashpayment")
public class CashPaymentController {

	@Autowired
	private CashPaymentService cashPaymentService;
	
	@Autowired
	private SchoolService schoolService;

	@GetMapping("/")
	public ResponseEntity<List<CashPayment>> getAllCashPaymentData() {
		List<CashPayment> cashPayments = cashPaymentService.getAllData();

		return new ResponseEntity<List<CashPayment>>(cashPayments, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CashPayment> getCashPaymentDataById(@PathVariable long id) {
		CashPayment cashPayments = cashPaymentService.getById(id);

		return new ResponseEntity<CashPayment>(cashPayments, HttpStatus.OK);
	}

	@GetMapping("/getbyudise/{udise}")
	public ResponseEntity<List<CashPayment>> getcashPaymentDataByUdise(@PathVariable long id) {

		School school = schoolService.getbyid(id);

		List<CashPayment> cashPayments = cashPaymentService.getbySchoolUdise(school);

		return new ResponseEntity<List<CashPayment>>(cashPayments, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<CashPayment> saveCashPaymentData(@RequestBody CashPaymentDto cashPaymentDto) {
		return null;
	}

	@PostMapping("/{id}")
	public ResponseEntity<CashPayment> editCashPaymentData(@RequestBody CashPaymentDto cashPaymentDto,
			@PathVariable long id) {
		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCashPaymentData(@PathVariable long id) {
		cashPaymentService.deleteData(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
