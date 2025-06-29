package com.api.controller.account;

import java.util.Arrays;
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

import com.api.dto.account.ExpensesVoucharDto;
import com.api.entity.account.ExpensesVouchar;
import com.api.entity.account.GeneralLedger;
import com.api.entity.account.SubHeadMaster;
import com.api.service.SchoolService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.ExpensesVoucharService;
import com.api.service.account.GeneralLedgerService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.SubHeadMasterService;

@RestController
@RequestMapping("/expensesvouchar")
public class ExpensesVoucharController {

	@Autowired
	private ExpensesVoucharService expensesVoucharService;

	@Autowired
	private SubHeadMasterService subHeadMasterService;

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private CustomerMasterService customerMasterService;

	@Autowired
	private HeadMasterService headMasterService;

	@Autowired
	private GeneralLedgerService generalLedgerService;

	@GetMapping("/")
	public ResponseEntity<List<ExpensesVouchar>> getExpensesVoucharAllData() {
		List<ExpensesVouchar> expensesVouchars = expensesVoucharService.getAllData();

		return new ResponseEntity<>(expensesVouchars, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExpensesVouchar> getExpensesVouchargetById(@PathVariable long id) {
		ExpensesVouchar expensesVouchar = expensesVoucharService.getById(id);
		return new ResponseEntity<>(expensesVouchar, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ExpensesVouchar> saveExpensesVoucharData(@RequestBody ExpensesVoucharDto expensesVoucharDto) {

		final List<String> subheadList = Arrays.asList("Direct Incomes",
		        "Indirect Incomes",
		        "Income (Direct)",
		        "Income (Indirect)",
		        "Sales Accounts",
		        "Retained Earnings"
		        );
		final List<String> expenseSubheadList = Arrays.asList("Direct Expenses",
		        "Indirect Expenses",
		        "Expenses (Direct)",
		        "Expenses (Indirect)",
		        "Purchase Accounts"
		        );
		SubHeadMaster customerSubhead = subHeadMasterService.getById(expensesVoucharDto.getCustId());
		SubHeadMaster expenseSubhead = subHeadMasterService.getById(expensesVoucharDto.getSubheadId());

		String bookSideName = customerSubhead.getHeadId().getBookSideMaster().getBooksideName();
		String subheadHeadName = expenseSubhead.getHeadId().getHeadName();
		ExpensesVouchar expensesVouchar = new ExpensesVouchar();
		expensesVouchar.setEntryDate(expensesVoucharDto.getEntryDate());
		expensesVouchar.setCreateDate(expensesVoucharDto.getEntryDate());
		expensesVouchar.setTranType(expensesVoucharDto.getTranType());
		expensesVouchar.setNarr(expensesVoucharDto.getNarr());
		expensesVouchar.setSubheadId(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()));
		expensesVouchar.setSchoolUdise(schoolService.getbyid(expensesVoucharDto.getSchoolUdise()));
		expensesVouchar.setHeadId(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()).getHeadId());
		expensesVouchar.setAmount(expensesVoucharDto.getAmount());
		expensesVouchar.setVoucharType(expensesVoucharDto.getVoucharType());
		expensesVouchar.setYear(expensesVoucharDto.getYear());

		ExpensesVouchar saveExpensesVouchar = expensesVoucharService.postData(expensesVouchar);
		GeneralLedger crGeneralLedger = new GeneralLedger();

		crGeneralLedger.setEntryNo(saveExpensesVouchar.getEntryNo());
		crGeneralLedger.setEntryType(expensesVoucharDto.getTranType());
		crGeneralLedger.setEntrydate(expensesVoucharDto.getEntryDate());
		crGeneralLedger.setShopId(schoolService.getbyid(expensesVoucharDto.getSchoolUdise()));
		crGeneralLedger.setCustId(customerMasterService.getById(expensesVoucharDto.getCustId()));
		crGeneralLedger.setHeadId(customerMasterService.getById(expensesVoucharDto.getCustId()).getHeadId());
		crGeneralLedger.setSubhead(subHeadMasterService.getById(expensesVoucharDto.getCustId()));
		crGeneralLedger.setNarr(expensesVoucharDto.getNarr());
		crGeneralLedger.setYear(expensesVoucharDto.getYear());
		System.out.println(subHeadMasterService.getById(expensesVoucharDto.getCustId()).getHeadId().getBookSideMaster().getBooksideName());
		System.out.println(subheadList.contains(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()).getHeadId().getHeadName()));
		System.out.println(expenseSubheadList.contains(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()).getHeadId().getHeadName()));
		if(bookSideName.equals("Asset") && subheadList.contains(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()).getHeadId().getHeadName())) {
			crGeneralLedger.setDrAmt(expensesVoucharDto.getAmount());
		}else if(bookSideName.equals("Liabilities") && subheadList.contains(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()).getHeadId().getHeadName())) {
			crGeneralLedger.setCrAmt(expensesVoucharDto.getAmount());
		}else if(bookSideName.equals("Liabilities") && expenseSubheadList.contains(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()).getHeadId().getHeadName())) {
			crGeneralLedger.setDrAmt(expensesVoucharDto.getAmount());
		}else if(bookSideName.equals("Asset") && expenseSubheadList.contains(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()).getHeadId().getHeadName())) {
				crGeneralLedger.setCrAmt(expensesVoucharDto.getAmount());
		}
//		generalLedger.setYear(0);

		GeneralLedger saveCrGeneralLedger = generalLedgerService.post(crGeneralLedger);

		GeneralLedger drGeneralLedger = new GeneralLedger();

		drGeneralLedger.setEntryNo(saveExpensesVouchar.getEntryNo());
		drGeneralLedger.setEntryType(expensesVoucharDto.getTranType());
		drGeneralLedger.setEntrydate(expensesVoucharDto.getEntryDate());
		drGeneralLedger.setShopId(schoolService.getbyid(expensesVoucharDto.getSchoolUdise()));
		drGeneralLedger.setHeadId(headMasterService.getById(expensesVoucharDto.getHeadId()));
		drGeneralLedger.setSubhead(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()));
		drGeneralLedger.setNarr(expensesVoucharDto.getNarr());
		drGeneralLedger.setYear(expensesVoucharDto.getYear());
		if(bookSideName.equals("Asset") && subheadList.contains(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()).getHeadId().getHeadName())) {
			drGeneralLedger.setCrAmt(expensesVoucharDto.getAmount());
		}else if(bookSideName.equals("Liabilities") && subheadList.contains(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()).getHeadId().getHeadName())) {
			drGeneralLedger.setCrAmt(expensesVoucharDto.getAmount());
		}else if(bookSideName.equals("Asset") && expenseSubheadList.contains(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()).getHeadId().getHeadName())) {
			drGeneralLedger.setDrAmt(expensesVoucharDto.getAmount());
		}
		else {
			drGeneralLedger.setDrAmt(expensesVoucharDto.getAmount());
		}
			generalLedgerService.post(drGeneralLedger);
		return new ResponseEntity<>(saveExpensesVouchar, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ExpensesVouchar> editExpensesVouchar(@PathVariable long id,
			@RequestBody ExpensesVoucharDto expensesVoucharDto) {
		ExpensesVouchar expensesVouchar = expensesVoucharService.getById(id);

		if (expensesVouchar == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		expensesVouchar.setEntryDate(expensesVoucharDto.getEntryDate());
		expensesVouchar.setCreateDate(expensesVoucharDto.getEntryDate());
		expensesVouchar.setTranType(expensesVoucharDto.getTranType());
		expensesVouchar.setNarr(expensesVoucharDto.getNarr());
		expensesVouchar.setSubheadId(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()));
		expensesVouchar.setSchoolUdise(schoolService.getbyid(expensesVoucharDto.getSchoolUdise()));
		expensesVouchar.setHeadId(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()).getHeadId());
		expensesVouchar.setAmount(expensesVoucharDto.getAmount());
		expensesVouchar.setVoucharType(expensesVoucharDto.getVoucharType());

		ExpensesVouchar saveExpensesVouchar = expensesVoucharService.postData(expensesVouchar);

//		GeneralLedger crGeneralLedger = generalLedgerService.getByEntryNoAndSubhead(expensesVouchar.getEntryNo(),
//				customerMasterService.getById(expensesVouchar.get);
//		if (crGeneralLedger != null) {
//			crGeneralLedger.setEntryNo(saveExpensesVouchar.getEntryNo());
//			crGeneralLedger.setEntryType(expensesVoucharDto.getTranType());
//			crGeneralLedger.setEntrydate(expensesVoucharDto.getEntryDate());
//			crGeneralLedger.setShopId(schoolService.getbyid(expensesVoucharDto.getSchoolUdise()));
//			crGeneralLedger.setCustId(customerMasterService.getById(expensesVoucharDto.getCustId()));
//			crGeneralLedger.setHeadId(customerMasterService.getById(expensesVoucharDto.getCustId()).getHeadId());
//			crGeneralLedger.setSubhead(customerMasterService.getById(expensesVoucharDto.getCustId()).getSubheadId());
//			crGeneralLedger.setNarr(expensesVoucharDto.getNarr());
//			crGeneralLedger.setCrAmt(expensesVoucharDto.getAmount());
//
//			GeneralLedger saveCrGeneralLedger = generalLedgerService.post(crGeneralLedger);
//		}
//
//		GeneralLedger drGeneralLedger = generalLedgerService.getByEntryNoAndSubhead(expensesVouchar.getEntryNo(),
//				expensesVoucharDto.getSubheadId());
//
//		if (drGeneralLedger != null) {
//			drGeneralLedger.setEntryNo(saveExpensesVouchar.getEntryNo());
//			drGeneralLedger.setEntryType(expensesVoucharDto.getTranType());
//			drGeneralLedger.setEntrydate(expensesVoucharDto.getEntryDate());
//			drGeneralLedger.setShopId(schoolService.getbyid(expensesVoucharDto.getSchoolUdise()));
//			drGeneralLedger.setHeadId(headMasterService.getById(expensesVoucharDto.getHeadId()));
//			drGeneralLedger.setSubhead(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()));
//			drGeneralLedger.setNarr(expensesVoucharDto.getNarr());
//			drGeneralLedger.setDrAmt(expensesVoucharDto.getAmount());
//
//			generalLedgerService.post(drGeneralLedger);
//		}

		return new ResponseEntity<>(saveExpensesVouchar,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteExpensesVouchaeData(@PathVariable long id)
	{
		ExpensesVouchar expensesVouchar=expensesVoucharService.getById(id);
		
		if(expensesVouchar==null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		long entryNo=expensesVouchar.getEntryNo();
		
		return null;
		
	}

}
