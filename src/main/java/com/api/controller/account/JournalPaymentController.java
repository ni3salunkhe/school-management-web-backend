package com.api.controller.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.account.JournalPaymentDto;
import com.api.dto.account.JournalPaymentDto.Entry;
import com.api.entity.account.GeneralLedger;
import com.api.entity.account.JournalPayment;
import com.api.service.SchoolService;
import com.api.service.account.GeneralLedgerService;
import com.api.service.account.JournalPaymentService;
import com.api.service.account.SubHeadMasterService;

@RestController
@RequestMapping("/journal")
public class JournalPaymentController {

	@Autowired
	private JournalPaymentService journalPaymentService;

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private SubHeadMasterService subHeadMasterService;

	@Autowired
	private GeneralLedgerService generalLedgerService;

	@GetMapping("/{udiseNo}")
	public ResponseEntity<List<JournalPayment>> getDataByUdiseNo(@PathVariable long udiseNo) {

		List<JournalPayment> journalPayments = journalPaymentService.getDataByUdise(udiseNo);

		return new ResponseEntity<>(journalPayments, HttpStatus.OK);
	}

	@GetMapping("/transactionkey")
	public ResponseEntity<Long> getTransactionKey() {

		Long latestKey = journalPaymentService.getTrasactionKey();

		long nextKey = (latestKey != null) ? latestKey + 1 : 1L;
		return ResponseEntity.ok(nextKey);
	}
	@PostMapping("/")
	public ResponseEntity<JournalPayment> saveJournalPayment(@RequestBody JournalPaymentDto journalPaymentDto) {
	    Long latestKey = journalPaymentService.getTrasactionKey();
	    long nextKey = (latestKey != null) ? latestKey + 1 : 1L;

	    // Save Credit Entries (changed from Debit Entries)
	    for (Entry entry : journalPaymentDto.getEntries()) {
	        JournalPayment credit = new JournalPayment();
	        credit.setEntryDate(journalPaymentDto.getEntryDate());
	        credit.setNarr(journalPaymentDto.getNarr());
	        credit.setTransactionKey(nextKey);
	        credit.setSchoolUdise(schoolService.getbyid(journalPaymentDto.getSchoolUdise()));
	        // Changed from getDebitaccount() to getCreditAccount()
	        credit.setSubheadId(subHeadMasterService.getById(entry.getCreditAccount()));
	        credit.setHeadId(subHeadMasterService.getById(entry.getCreditAccount()).getHeadId());
	        credit.setAmount(entry.getAmount());
	        credit.setTranType(journalPaymentDto.getTranType());
	        journalPaymentService.postData(credit);
	    }

	    GeneralLedger drgeneralLedger = new GeneralLedger();
	    drgeneralLedger.setEntryNo(nextKey);
	    drgeneralLedger.setEntryType(journalPaymentDto.getTranType());
	    drgeneralLedger.setEntrydate(journalPaymentDto.getEntryDate());
	    drgeneralLedger.setShopId(schoolService.getbyid(journalPaymentDto.getSchoolUdise()));
	    
	    // Changed from getCreditAccount() to getDebitaccount()
//	    String bookside1 = subHeadMasterService.getById(journalPaymentDto.getDebitaccount()).getHeadId()
//	            .getBookSideMaster().getBooksideName();
//
//	    if (bookside1.equals("Asset") || bookside1.equals("Profit And Loss")) {
//	        drgeneralLedger.setDrAmt(journalPaymentDto.getCramount());
//	    } else if (bookside1.equals("Liabilities")) {
//	        drgeneralLedger.setCrAmt(journalPaymentDto.getCramount());
//	    }
	    
	    drgeneralLedger.setDrAmt(journalPaymentDto.getDramount());
	    
	    drgeneralLedger.setNarr(journalPaymentDto.getNarr());
	    // Changed from getCreditAccount() to getDebitaccount()
	    drgeneralLedger.setHeadId(subHeadMasterService.getById(journalPaymentDto.getDebitaccount()).getHeadId());
	    drgeneralLedger.setSubhead(subHeadMasterService.getById(journalPaymentDto.getDebitaccount()));
	    generalLedgerService.post(drgeneralLedger);

	    for (Entry entry : journalPaymentDto.getEntries()) {
	        GeneralLedger crgeneralLedger = new GeneralLedger();
	        crgeneralLedger.setEntryNo(nextKey);
	        crgeneralLedger.setEntryType(journalPaymentDto.getTranType());
	        crgeneralLedger.setEntrydate(journalPaymentDto.getEntryDate());
	        crgeneralLedger.setShopId(schoolService.getbyid(journalPaymentDto.getSchoolUdise()));
	        
	        // Changed from getDebitaccount() to getCreditAccount()
//	        String bookside = subHeadMasterService.getById(entry.getCreditAccount()).getHeadId().getBookSideMaster()
//	                .getBooksideName();
//	        
//	        if (bookside.equals("Asset") || bookside.equals("Profit And Loss")) {
//	            crgeneralLedger.setCrAmt(entry.getAmount());
//	        } else if (bookside.equals("Liabilities")) {
//	            crgeneralLedger.setDrAmt(entry.getAmount());
//	        }
	        
	        crgeneralLedger.setCrAmt(entry.getAmount());

	        crgeneralLedger.setNarr(journalPaymentDto.getNarr());
	        // Changed from getDebitaccount() to getCreditAccount()
	        crgeneralLedger.setHeadId(subHeadMasterService.getById(entry.getCreditAccount()).getHeadId());
	        crgeneralLedger.setSubhead(subHeadMasterService.getById(entry.getCreditAccount()));
	        generalLedgerService.post(crgeneralLedger);
	    }

	    return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
