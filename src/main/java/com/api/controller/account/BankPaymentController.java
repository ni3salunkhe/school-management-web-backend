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

import com.api.dto.account.BankPaymentDto;
import com.api.entity.School;
import com.api.entity.account.BankPayment;
import com.api.service.SchoolService;
import com.api.service.account.BankPaymentService;

@RestController
@RequestMapping("/bankpayment")
public class BankPaymentController {
	
	
	@Autowired
	private BankPaymentService bankPaymentService;
	
	@Autowired
	private SchoolService schoolService;
	
	@GetMapping("/")
	public ResponseEntity<List<BankPayment>> getAllBankPaymentData()
	{
		List<BankPayment> bankPayments=bankPaymentService.getAllData();
		
		return new ResponseEntity<List<BankPayment>>(bankPayments,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BankPayment> getBankPaymentById(@PathVariable long id)
	{
		BankPayment bankPayment=bankPaymentService.getById(id);
		
		return new ResponseEntity<BankPayment>(bankPayment,HttpStatus.OK);
	}
	
	@GetMapping("/getbyudise/{udise}")
	public ResponseEntity<List<BankPayment>> getBankPaymentDataByUdise(@PathVariable long id)
	{
		
		School school=schoolService.getbyid(id);
		
		List<BankPayment> bankPayments=bankPaymentService.getbySchoolUdise(school);
		
		return new ResponseEntity<List<BankPayment>>(bankPayments,HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<BankPayment> saveBankPaymentData(@RequestBody BankPaymentDto bankPaymentDto)
	{
		
		BankPayment bankPayment=new BankPayment();
		
		bankPayment.setEntryDate(bankPaymentDto.getEntryDate());
//		bankPayment party id
		bankPayment.setTranType(bankPaymentDto.getTranType());
		bankPayment.setAmount(bankPaymentDto.getAmount());
		bankPayment.setNarr(bankPaymentDto.getNarr());
		
		return null;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BankPayment> editBankPaymentData(@RequestBody BankPaymentDto bankPaymentDto)
	{
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBankPaymentData(@PathVariable long id)
	{
		bankPaymentService.deleteData(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
