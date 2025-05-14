package com.api.controller;

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

import com.api.dto.ModuleTransactionDto;
import com.api.entity.ModuleMaster;
import com.api.entity.ModuleTransaction;
import com.api.entity.School;
import com.api.service.ModuleMasterService;
import com.api.service.ModuleTransactionService;
import com.api.service.SchoolService;

@RestController
@RequestMapping("/moduletrasaction")
public class ModuleTransactionController {

	@Autowired
	private ModuleTransactionService moduleTransactionService;

	@Autowired
	private ModuleMasterService moduleMasterService;

	@Autowired
	private SchoolService schoolService;

	@GetMapping("/")
	public ResponseEntity<List<ModuleTransaction>> getAll() {
		List<ModuleTransaction> moduleTransactions = moduleTransactionService.getAllData();

		return new ResponseEntity<List<ModuleTransaction>>(moduleTransactions, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ModuleTransaction> getDataById(@PathVariable long id) {
		ModuleTransaction moduleTransaction = moduleTransactionService.getById(id);

		return new ResponseEntity<ModuleTransaction>(moduleTransaction, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ModuleTransaction> saveData(@RequestBody ModuleTransactionDto moduleTransactionDto) {
		ModuleTransaction moduleTransaction = new ModuleTransaction();
		moduleTransaction.setModuleMaster(moduleMasterService.getById(moduleTransactionDto.getModuleMaster()));
		moduleTransaction.setSchool(schoolService.getbyid(moduleTransactionDto.getSchool()));

		ModuleTransaction saveModuleTransaction = moduleTransactionService.post(moduleTransaction);

		return new ResponseEntity<ModuleTransaction>(saveModuleTransaction, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ModuleTransaction> putData(@RequestBody ModuleTransactionDto moduleTransactionDto,
			@PathVariable long id) {
		ModuleTransaction moduleTransaction = moduleTransactionService.getById(id);

		moduleTransaction.setModuleMaster(moduleMasterService.getById(moduleTransactionDto.getModuleMaster()));
		moduleTransaction.setSchool(schoolService.getbyid(moduleTransactionDto.getSchool()));

		ModuleTransaction saveModuleTransaction = moduleTransactionService.post(moduleTransaction);

		return new ResponseEntity<ModuleTransaction>(saveModuleTransaction, HttpStatus.OK);
	}
	
	@GetMapping("/getbyschooludise/{id}")
	public ResponseEntity<List<ModuleTransaction>> getDataBySchoolUdise(@PathVariable long id)
	{
		School school=schoolService.getbyid(id);
		
		List<ModuleTransaction> moduleTransactions=moduleTransactionService.getBySchoolUdise(school);
		
		return new ResponseEntity<List<ModuleTransaction>>(moduleTransactions,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDataById(@PathVariable long id)
	{
		moduleMasterService.deleteData(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
