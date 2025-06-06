package com.api.controller.account;

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

import com.api.dto.account.BankReciptDto;
import com.api.entity.School;
import com.api.entity.account.BankMaster;
import com.api.entity.account.BankReceipt;
import com.api.entity.account.GeneralLedger;
import com.api.service.SchoolService;
import com.api.service.account.BankMasterService;
import com.api.service.account.BankReceiptService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.GeneralLedgerService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.SubHeadMasterService;

@RestController
@RequestMapping("/bankreceipt")
public class BankReceiptController {

	@Autowired
	private BankReceiptService bankReceiptService;

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

	@PostMapping(value = "/", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BankReceipt> saveBankReciptData(@ModelAttribute BankReciptDto bankReciptDto) {
		try {
			BankReceipt bankReceipt=new BankReceipt();
			bankReceipt.setEntryDate(bankReciptDto.getEntryDate());
			bankReceipt.setCreateDate(bankReciptDto.getCreateDate());
			bankReceipt.setModifieDate(bankReciptDto.getModifieDate());
			bankReceipt.setTranType(bankReciptDto.getTranType());
			bankReceipt.setAmount(bankReciptDto.getAmount());
			bankReceipt.setNarr(bankReciptDto.getNarr());
//			bankReceipt.setYear(bankReciptDto.getYear());
			bankReceipt.setPaymentType(bankReciptDto.getPaymentType());
			bankReceipt.setBillNo(bankReciptDto.getBillNo());
			bankReceipt.setStatus(bankReciptDto.getStatus());
			bankReceipt.setCustId(customerMasterService.getById(bankReciptDto.getCustId()));
			bankReceipt.setBankId(bankMasterService.getbyid(bankReciptDto.getBankId()));
			bankReceipt.setSchoolUdise(schoolService.getbyid(bankReciptDto.getSchoolUdise()));
			bankReceipt.setHeadId(headMasterService.getById(bankReciptDto.getHeadId()));
			bankReceipt.setSubheadId(subHeadMasterService.getById(bankReciptDto.getSubheadId()));

			if(bankReciptDto.getImg() != null && !bankReciptDto.getImg().isEmpty())
			{
				try {
					bankReceipt.setImg(bankReciptDto.getImg().getBytes());
				}
				catch(IOException e)
				{
					e.printStackTrace();
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

				}
			}
			
			BankReceipt saveBankReceipt=bankReceiptService.postData(bankReceipt);
			
			BankMaster bankMaster=bankMasterService.getbyid(bankReciptDto.getBankId());
			
			GeneralLedger drGeneralLedger=new GeneralLedger();
			drGeneralLedger.setDr_Amt(bankReciptDto.getAmount());
			drGeneralLedger.setCustId(bankMaster.getCustId());
			drGeneralLedger.setEntrydate(bankReciptDto.getEntryDate());
			drGeneralLedger.setEntryNo(saveBankReceipt.getEntryNo());
			drGeneralLedger.setEntryType(bankReciptDto.getTranType());
			drGeneralLedger.setHeadId(bankMaster.getHeadId());
			drGeneralLedger.setNarr(bankReciptDto.getNarr());
			drGeneralLedger.setShopId(schoolService.getbyid(bankReciptDto.getSchoolUdise()));
//			drGeneralLedger.setSubhead(subHeadMasterService.getById(bankPaymentDto.getSubheadId()));
			
			generalLedgerService.post(drGeneralLedger);
			
			GeneralLedger crGeneralLedger=new GeneralLedger();
			crGeneralLedger.setCr_Amt(bankReciptDto.getAmount());
			crGeneralLedger.setCustId(customerMasterService.getById(bankReciptDto.getCustId()));
			crGeneralLedger.setEntrydate(bankReciptDto.getEntryDate());
			crGeneralLedger.setEntryNo(bankReciptDto.getEntryNo());
			crGeneralLedger.setEntryType(bankReciptDto.getTranType());
			crGeneralLedger.setHeadId(headMasterService.getById(bankReciptDto.getHeadId()));
			crGeneralLedger.setNarr(bankReciptDto.getNarr());
			crGeneralLedger.setShopId(schoolService.getbyid(bankReciptDto.getSchoolUdise()));
			crGeneralLedger.setSubhead(subHeadMasterService.getById(bankReciptDto.getSubheadId()));
//			crGeneralLedger.setYear("");
			
			generalLedgerService.post(crGeneralLedger);
			
			return new ResponseEntity<>(saveBankReceipt,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
