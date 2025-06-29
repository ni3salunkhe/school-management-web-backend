package com.api.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.dto.account.CashPaymentDto;
import com.api.dto.account.ContraPaymentDto;
import com.api.entity.account.ContraPayment;
import com.api.entity.account.GeneralLedger;
import com.api.service.SchoolService;
import com.api.service.StaffService;
import com.api.service.account.ContraPaymentService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.GeneralLedgerService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.SubHeadMasterService;

@RestController
@RequestMapping("/contrapayment")
public class ContraPaymentController {

	@Autowired
	private GeneralLedgerService generalLedgerService;
	
	@Autowired
	private SubHeadMasterService subHeadMasterService;
	
	@Autowired
	private HeadMasterService headMasterService;
	
	@Autowired
	private CustomerMasterService customerMasterService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private ContraPaymentService contraPaymentService;
	
	@PostMapping(value="/", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ContraPayment> saveall(@ModelAttribute ContraPaymentDto dto){
		String headType = headMasterService.getById(dto.getHeadid()).getBookSideMaster().getBooksideName();
		String mainHeadType = headMasterService.getById(dto.getMainHeadId()).getBookSideMaster().getBooksideName();

		ContraPayment contraPayment = new ContraPayment();
		contraPayment.setSubheadId(subHeadMasterService.getById(dto.getMainSubHead()));
		contraPayment.setHeadId(headMasterService.getById(dto.getMainHeadId()));
		contraPayment.setCustId(customerMasterService.getById(dto.getMainSubHead()));
		contraPayment.setAmount(dto.getAmount());
		contraPayment.setEntryDate(dto.getEntrydate());
		contraPayment.setEntryType(dto.getEntryType());
		contraPayment.setModifieDate(dto.getEntrydate());
		contraPayment.setBillNo(dto.getBillno());
		contraPayment.setCreateDate(dto.getEntrydate());
		contraPayment.setNarr(dto.getNarr());
		contraPayment.setSchoolUdise(schoolService.getbyid(dto.getUdiseNo()));
		contraPayment.setStaffId(staffService.getbyid(dto.getStaffId()));
		contraPayment.setCreateDate(dto.getEntrydate());
		contraPayment.setYear(dto.getYear());
		
		ContraPayment contraPayment2 = contraPaymentService.post(contraPayment);
		
		GeneralLedger drEntry = new GeneralLedger();
		drEntry.setEntryNo(contraPayment2.getEntryNo());
		drEntry.setCustId(customerMasterService.getById(dto.getSubhead()));
		drEntry.setEntryType(dto.getEntryType());
		drEntry.setHeadId(headMasterService.getById(dto.getHeadid()));
		drEntry.setSubhead(subHeadMasterService.getById(dto.getSubhead()));
		drEntry.setNarr(dto.getNarr());
		drEntry.setShopId(schoolService.getbyid(dto.getUdiseNo()));
		drEntry.setEntrydate(dto.getEntrydate());
		drEntry.setYear(dto.getYear());
		if(headType.equals("Asset") || headType.equals("Profit And Loss")) {
			drEntry.setDrAmt(dto.getAmount());
		}
		else if(headType.equals("Liabilities")) {
			drEntry.setCrAmt(dto.getAmount());
		}
		GeneralLedger saveGeneralLedger1 = generalLedgerService.post(drEntry);

		GeneralLedger crEntry = new GeneralLedger();
		crEntry.setEntryNo(contraPayment2.getEntryNo());
		if(mainHeadType.equals("Asset") || mainHeadType.equals("Profit And Loss")) {
			crEntry.setCrAmt(dto.getAmount());
		}
		else if(mainHeadType.equals("Liabilities")) {
			crEntry.setDrAmt(dto.getAmount());
		}
		crEntry.setEntryType(dto.getEntryType());
		crEntry.setNarr(dto.getNarr());
		crEntry.setCustId(customerMasterService.getById(dto.getMainSubHead()));
		crEntry.setHeadId(headMasterService.getById(dto.getMainHeadId()));
		crEntry.setSubhead(subHeadMasterService.getById(dto.getMainSubHead()));
		crEntry.setShopId(schoolService.getbyid(dto.getUdiseNo()));
		crEntry.setEntrydate(dto.getEntrydate());
		crEntry.setYear(dto.getYear());
		
		GeneralLedger saveGeneralLedger = generalLedgerService.post(crEntry);
		
		return new ResponseEntity<ContraPayment>(contraPayment2, HttpStatus.OK);
	}
}
