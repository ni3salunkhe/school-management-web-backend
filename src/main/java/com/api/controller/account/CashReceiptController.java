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

import com.api.dto.account.CashReceiptDto;
import com.api.entity.School;
import com.api.entity.account.CashPayment;
import com.api.entity.account.CashReceipt;
import com.api.entity.account.GeneralLedger;
import com.api.service.SchoolService;
import com.api.service.account.CashReceiptService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.GeneralLedgerService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.SubHeadMasterService;

@RestController
@RequestMapping("/cashreceipt")
public class CashReceiptController {

	@Autowired
	private CashReceiptService cashReceiptService;

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private CustomerMasterService customerMasterService;

	@Autowired
	private HeadMasterService headMasterService;

	@Autowired
	private SubHeadMasterService subHeadMasterService;

	@Autowired
	private GeneralLedgerService generalLedgerService;

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
	public ResponseEntity<List<CashReceipt>> getCashReceiptDataByUdise(@PathVariable long udise) {

		School school = schoolService.getbyid(udise);

		List<CashReceipt> cashReceipts = cashReceiptService.getbySchoolUdise(school);

		return new ResponseEntity<List<CashReceipt>>(cashReceipts, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<CashReceipt> saveCashReceiptData(@RequestBody CashReceiptDto cashReceiptDto) {

		CashReceipt cashReceipt = new CashReceipt();
		cashReceipt.setEntryDate(cashReceiptDto.getEntryDate());
		cashReceipt.setCustId(customerMasterService.getById(cashReceiptDto.getCustId()));
		cashReceipt.setTranType(cashReceiptDto.getTranType());
		cashReceipt.setAmount(cashReceiptDto.getAmount());
		cashReceipt.setNarr(cashReceiptDto.getNarr());
		cashReceipt.setSchoolUdise(schoolService.getbyid(cashReceiptDto.getSchoolUdise()));
		cashReceipt.setHeadId(headMasterService.getById(cashReceiptDto.getHeadId()));
		cashReceipt.setSubheadId(subHeadMasterService.getById(cashReceiptDto.getSubheadId()));
		cashReceipt.setCreateDate(cashReceiptDto.getCreateDate());
		cashReceipt.setModifieDate(cashReceiptDto.getModifieDate());
		cashReceipt.setBillNo(cashReceiptDto.getBillNo());
		cashReceipt.setBillType(cashReceiptDto.getBillType());
		cashReceipt.setSaleDup(cashReceiptDto.getSaleDup());
		cashReceipt.setStatus(cashReceiptDto.getStatus());
		System.out.println(cashReceipt);

		CashReceipt saveCashReceipt = cashReceiptService.postData(cashReceipt);

		GeneralLedger drgeneralLedger = new GeneralLedger();
		drgeneralLedger.setEntryNo(saveCashReceipt.getEntryNo());
		drgeneralLedger.setEntryType(cashReceiptDto.getTranType());
		drgeneralLedger.setEntrydate(cashReceiptDto.getEntryDate());
		drgeneralLedger.setShopId(schoolService.getbyid(cashReceiptDto.getSchoolUdise()));
//		drgeneralLedger.setCustId(customerMasterService.getById(cashReceiptDto.getCustId()));
		drgeneralLedger.setDrAmt(cashReceiptDto.getAmount());
		drgeneralLedger.setNarr(cashReceiptDto.getNarr());
		drgeneralLedger.setHeadId(headMasterService.getByHeadName("Cash In Hand"));
//		System.out.println(headMasterService.getByHeadName("Cash In Hand"));
//		drgeneralLedger.setSubhead(subHeadMasterService.getById(cashReceiptDto.getSubheadId()));

		GeneralLedger saveDrGeneralLedger = generalLedgerService.post(drgeneralLedger);

		GeneralLedger crgeneralLedger = new GeneralLedger();
		crgeneralLedger.setEntryNo(saveCashReceipt.getEntryNo());
		drgeneralLedger.setEntryType(cashReceiptDto.getTranType());
		crgeneralLedger.setEntrydate(cashReceiptDto.getEntryDate());
		crgeneralLedger.setShopId(schoolService.getbyid(cashReceiptDto.getSchoolUdise()));
		crgeneralLedger.setCustId(customerMasterService.getById(cashReceiptDto.getCustId()));
		crgeneralLedger.setCrAmt(cashReceiptDto.getAmount());
		crgeneralLedger.setNarr(cashReceiptDto.getNarr());
		crgeneralLedger.setHeadId(headMasterService.getById(cashReceiptDto.getHeadId()));
		crgeneralLedger.setSubhead(subHeadMasterService.getById(cashReceiptDto.getSubheadId()));

		GeneralLedger saveCrGeneralLedger = generalLedgerService.post(crgeneralLedger);

		return new ResponseEntity<CashReceipt>(saveCashReceipt, HttpStatus.OK);
	}

	@PutMapping("/{id}")
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
