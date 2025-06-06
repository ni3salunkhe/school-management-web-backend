package com.api.controller.account;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.account.BankPaymentDto;
import com.api.entity.School;
import com.api.entity.account.BankMaster;
import com.api.entity.account.BankPayment;
import com.api.entity.account.GeneralLedger;
import com.api.service.SchoolService;
import com.api.service.account.BankMasterService;
import com.api.service.account.BankPaymentService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.GeneralLedgerService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.SubHeadMasterService;

@RestController
@RequestMapping("/bankpayment")
public class BankPaymentController {

	@Autowired
	private BankPaymentService bankPaymentService;

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private CustomerMasterService customerMasterService;

	@Autowired
	private BankMasterService bankMasterService;

	@Autowired
	private HeadMasterService headMasterService;

	@Autowired
	private SubHeadMasterService subHeadMasterService;
	
	@Autowired
	private GeneralLedgerService generalLedgerService;

	@GetMapping("/")
	public ResponseEntity<List<BankPayment>> getAllBankPaymentData() {
		List<BankPayment> bankPayments = bankPaymentService.getAllData();

		return new ResponseEntity<List<BankPayment>>(bankPayments, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BankPayment> getBankPaymentById(@PathVariable long id) {
		BankPayment bankPayment = bankPaymentService.getById(id);

		return new ResponseEntity<BankPayment>(bankPayment, HttpStatus.OK);
	}

	@GetMapping("/getbyudise/{udise}")
	public ResponseEntity<List<BankPayment>> getBankPaymentDataByUdise(@PathVariable long id) {

		School school = schoolService.getbyid(id);

		List<BankPayment> bankPayments = bankPaymentService.getbySchoolUdise(school);

		return new ResponseEntity<List<BankPayment>>(bankPayments, HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BankPayment> saveBankPaymentData(@ModelAttribute BankPaymentDto bankPaymentDto) {
		try {
			BankPayment bankPayment = new BankPayment();

			// Set basic fields
//			bankPayment.setVoucherNo(bankPaymentDto.getVoucherNo());
			bankPayment.setEntryDate(bankPaymentDto.getEntryDate());
			bankPayment.setCreateDate(bankPaymentDto.getCreateDate());
			bankPayment.setModifieDate(bankPaymentDto.getModifieDate());
			bankPayment.setTranType(bankPaymentDto.getTranType());
			bankPayment.setAmount(bankPaymentDto.getAmount());
			bankPayment.setNarr(bankPaymentDto.getNarr());
			bankPayment.setYear(bankPaymentDto.getYear());
			bankPayment.setPaymentType(bankPaymentDto.getPaymentType());
			bankPayment.setBillNo(bankPaymentDto.getBillNo());
			bankPayment.setStatus(bankPaymentDto.getStatus());

			// Set relationships with null checks

			bankPayment.setCustId(customerMasterService.getById(bankPaymentDto.getCustId()));

			bankPayment.setBankId(bankMasterService.getbyid(bankPaymentDto.getBankId()));

			bankPayment.setSchoolUdise(schoolService.getbyid(bankPaymentDto.getSchoolUdise()));

			bankPayment.setHeadId(headMasterService.getById(bankPaymentDto.getHeadId()));

			bankPayment.setSubheadId(subHeadMasterService.getById(bankPaymentDto.getSubheadId()));

			// Handle image upload
			if (bankPaymentDto.getImg() != null && !bankPaymentDto.getImg().isEmpty()) {
				try {
					bankPayment.setImg(bankPaymentDto.getImg().getBytes());
				} catch (IOException e) {
					e.printStackTrace();
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}

			BankPayment savedBankPayment = bankPaymentService.postData(bankPayment);
			
			BankMaster bankMaster=bankMasterService.getbyid(bankPaymentDto.getBankId());
			
			
			GeneralLedger crGeneralLedger=new GeneralLedger();
			crGeneralLedger.setCr_Amt(bankPaymentDto.getAmount());
			crGeneralLedger.setCustId(bankMaster.getCustId());
			crGeneralLedger.setEntrydate(bankPaymentDto.getEntryDate());
			crGeneralLedger.setEntryNo(savedBankPayment.getEntryNo());
			crGeneralLedger.setEntryType(bankPaymentDto.getTranType());
			crGeneralLedger.setHeadId(bankMaster.getHeadId());
			crGeneralLedger.setNarr(bankPaymentDto.getNarr());
			crGeneralLedger.setShopId(schoolService.getbyid(bankPaymentDto.getSchoolUdise()));
//			crGeneralLedger.setSubhead(subHeadMasterService.getById(bankPaymentDto.getSubheadId()));
//			crGeneralLedger.setYear("");
			
			generalLedgerService.post(crGeneralLedger);
			
			GeneralLedger drGeneralLedger=new GeneralLedger();
			drGeneralLedger.setDr_Amt(bankPaymentDto.getAmount());
			drGeneralLedger.setCustId(customerMasterService.getById(bankPaymentDto.getCustId()));
			drGeneralLedger.setEntrydate(bankPaymentDto.getEntryDate());
			drGeneralLedger.setEntryNo(savedBankPayment.getEntryNo());
			drGeneralLedger.setEntryType(bankPaymentDto.getTranType());
			drGeneralLedger.setHeadId(headMasterService.getById(bankPaymentDto.getHeadId()));
			drGeneralLedger.setNarr(bankPaymentDto.getNarr());
			drGeneralLedger.setShopId(schoolService.getbyid(bankPaymentDto.getSchoolUdise()));
			drGeneralLedger.setSubhead(subHeadMasterService.getById(bankPaymentDto.getSubheadId()));
			
			generalLedgerService.post(drGeneralLedger);
			
			return new ResponseEntity<>(savedBankPayment, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<BankPayment> editBankPaymentData(@RequestBody BankPaymentDto bankPaymentDto) {
		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBankPaymentData(@PathVariable long id) {
		bankPaymentService.deleteData(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
