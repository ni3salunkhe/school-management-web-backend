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

//		JournalPayment credit = new JournalPayment();
//		credit.setEntryDate(journalPaymentDto.getEntryDate());
//		credit.setNarr(journalPaymentDto.getNarr());
//		credit.setSchoolUdise(schoolService.getbyid(journalPaymentDto.getSchoolUdise()));
//		credit.setSubheadId(subHeadMasterService.getById(journalPaymentDto.getCreditAccount()));
//		credit.setHeadId(subHeadMasterService.getById(journalPaymentDto.getCreditAccount()).getHeadId());
//		credit.setAmount(journalPaymentDto.getCramount());
//		credit.setTranType(journalPaymentDto.getTranType());
//		journalPaymentService.postData(credit);

		Long latestKey = journalPaymentService.getTrasactionKey();

		long nextKey = (latestKey != null) ? latestKey + 1 : 1L;

		// Save Debit Entries
		for (Entry entry : journalPaymentDto.getEntries()) {
			JournalPayment debit = new JournalPayment();
			debit.setEntryDate(journalPaymentDto.getEntryDate());
			debit.setNarr(journalPaymentDto.getNarr());
			debit.setTransactionKey(nextKey);
			debit.setSchoolUdise(schoolService.getbyid(journalPaymentDto.getSchoolUdise()));
			debit.setSubheadId(subHeadMasterService.getById(entry.getDebitaccount()));
			debit.setHeadId(subHeadMasterService.getById(entry.getDebitaccount()).getHeadId());
			debit.setAmount(entry.getAmount());
			debit.setTranType(journalPaymentDto.getTranType());
			journalPaymentService.postData(debit);
		}

		GeneralLedger crgeneralLedger = new GeneralLedger();

		crgeneralLedger.setEntryNo(nextKey);
		crgeneralLedger.setEntryType(journalPaymentDto.getTranType());
		crgeneralLedger.setEntrydate(journalPaymentDto.getEntryDate());
		crgeneralLedger.setShopId(schoolService.getbyid(journalPaymentDto.getSchoolUdise()));
//		crgeneralLedger.setCustId(customerMasterService.getById(cashReceiptDto.getCustId()));
		String bookside1 = subHeadMasterService.getById(journalPaymentDto.getCreditAccount()).getHeadId()
				.getBookSideMaster().getBooksideName();

		if (bookside1.equals("Asset") || bookside1.equals("Profit And Loss")) {
			crgeneralLedger.setCrAmt(journalPaymentDto.getCramount());
		} else if (bookside1.equals("Liabilities")) {
			crgeneralLedger.setDrAmt(journalPaymentDto.getCramount());
		}
		crgeneralLedger.setNarr(journalPaymentDto.getNarr());
		crgeneralLedger.setHeadId(subHeadMasterService.getById(journalPaymentDto.getCreditAccount()).getHeadId());
		crgeneralLedger.setSubhead(subHeadMasterService.getById(journalPaymentDto.getCreditAccount()));

		GeneralLedger saveGeneralLedger = generalLedgerService.post(crgeneralLedger);

		for (Entry entry : journalPaymentDto.getEntries()) {
			GeneralLedger drgeneralLedger = new GeneralLedger();
			drgeneralLedger.setEntryNo(nextKey);
			drgeneralLedger.setEntryType(journalPaymentDto.getTranType());
			drgeneralLedger.setEntrydate(journalPaymentDto.getEntryDate());
			drgeneralLedger.setShopId(schoolService.getbyid(journalPaymentDto.getSchoolUdise()));
//			drgeneralLedger.setCustId(customerMaster);
			String bookside = subHeadMasterService.getById(entry.getDebitaccount()).getHeadId().getBookSideMaster()
					.getBooksideName();
			if (bookside.equals("Asset") || bookside.equals("Profit And Loss")) {
				drgeneralLedger.setDrAmt(entry.getAmount());
			} else if (bookside.equals("Liabilities")) {
				drgeneralLedger.setCrAmt(entry.getAmount());
			}

			drgeneralLedger.setNarr(journalPaymentDto.getNarr());
			drgeneralLedger.setHeadId(subHeadMasterService.getById(entry.getDebitaccount()).getHeadId());
			drgeneralLedger.setSubhead(subHeadMasterService.getById(entry.getDebitaccount()));

			generalLedgerService.post(drgeneralLedger);
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
