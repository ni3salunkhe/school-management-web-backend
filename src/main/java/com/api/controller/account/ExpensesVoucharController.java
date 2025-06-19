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

import com.api.dto.account.ExpensesVoucharDto;
import com.api.entity.account.ExpensesVouchar;
import com.api.entity.account.GeneralLedger;
import com.api.service.SchoolService;
import com.api.service.account.ExpensesVoucharService;
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
	
	@GetMapping("/")
	public ResponseEntity<List<ExpensesVouchar>> getExpensesVoucharAllData()
	{
		List<ExpensesVouchar> expensesVouchars=expensesVoucharService.getAllData();
		
		return new ResponseEntity<>(expensesVouchars,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ExpensesVouchar> getExpensesVouchargetById(@PathVariable long id)
	{
		ExpensesVouchar expensesVouchar=expensesVoucharService.getById(id);
		return new ResponseEntity<>(expensesVouchar,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ExpensesVouchar> saveExpensesVoucharData(@RequestBody ExpensesVoucharDto expensesVoucharDto)
	{
		
		ExpensesVouchar expensesVouchar=new ExpensesVouchar();
		expensesVouchar.setEntryDate(expensesVoucharDto.getEntryDate());
		expensesVouchar.setCreateDate(expensesVoucharDto.getEntryDate());
		expensesVouchar.setTranType(expensesVoucharDto.getTranType());
		expensesVouchar.setNarr(expensesVoucharDto.getNarr());
		expensesVouchar.setSubheadId(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()));
		expensesVouchar.setSchoolUdise(schoolService.getbyid(expensesVoucharDto.getSchoolUdise()));
		expensesVouchar.setHeadId(subHeadMasterService.getById(expensesVoucharDto.getSubheadId()).getHeadId());
		
		ExpensesVouchar saveExpensesVouchar=expensesVoucharService.postData(expensesVouchar);
		GeneralLedger generalLedger=new GeneralLedger();
		
		generalLedger.setEntryNo(saveExpensesVouchar.getEntryNo());
		return null;
	}
	
}
