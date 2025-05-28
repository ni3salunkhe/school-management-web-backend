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

import com.api.dto.account.CashPaymentDto;
import com.api.entity.School;
import com.api.entity.account.CashPayment;
import com.api.service.SchoolService;
import com.api.service.StaffService;
import com.api.service.account.CashPaymentService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.SubHeadMasterService;

@RestController
@RequestMapping("/cashpayment")
public class CashPaymentController {

	@Autowired
	private CashPaymentService cashPaymentService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private CustomerMasterService customerMasterService;
	
	@Autowired
	private HeadMasterService headMasterService;
	
	@Autowired
	private SubHeadMasterService subHeadMasterService;

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
	public ResponseEntity<List<CashPayment>> getcashPaymentDataByUdise(@PathVariable long udise) {

		List<CashPayment> cashPayments = cashPaymentService.getbySchoolUdise(udise);

		return new ResponseEntity<List<CashPayment>>(cashPayments, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<CashPayment> saveCashPaymentData(@RequestBody CashPaymentDto cashPaymentDto) {
		CashPayment cashPayment = new CashPayment();
		cashPayment.setAmount(cashPaymentDto.getAmount());
		cashPayment.setBillNo(cashPaymentDto.getBillNo());
		cashPayment.setBillType(cashPaymentDto.getBillType());
		cashPayment.setCustId(customerMasterService.getById(cashPaymentDto.getCustId()));
		cashPayment.setEntryDate(cashPaymentDto.getEntryDate());
		cashPayment.setEntryNo(cashPaymentDto.getEntryNo());
		cashPayment.setHeadId(headMasterService.getById(cashPaymentDto.getHeadId()));
		cashPayment.setSubheadId(subHeadMasterService.getById(cashPaymentDto.getSubheadId()));
		cashPayment.setNarr(cashPaymentDto.getNarr());
		School school = schoolService.getbyid(cashPaymentDto.getSchoolUdise());
		cashPayment.setSchoolUdise(school);
		cashPayment.setStaffId(staffService.getbyid(cashPaymentDto.getStaffId()));
		cashPayment.setStatus(cashPaymentDto.getStatus());
		cashPayment.setModifieDate(cashPaymentDto.getModifieDate());
		cashPayment.setCreateDate(cashPaymentDto.getCreateDate());
		cashPayment.setYear(cashPaymentDto.getYear());
		cashPayment.setTranType(cashPaymentDto.getTranType());
		
		CashPayment savedPayements= cashPaymentService.postData(cashPayment);
		return new ResponseEntity<CashPayment>(savedPayements, HttpStatus.OK);
		
	}

	@PutMapping("/{id}")
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
