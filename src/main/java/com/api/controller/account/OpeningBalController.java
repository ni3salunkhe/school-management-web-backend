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

import com.api.dto.account.OpeningBalDto;
import com.api.entity.account.OpeningBal;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.OpeningBalService;

@RestController
@RequestMapping("/openingbal")
public class OpeningBalController {

	@Autowired
	private OpeningBalService openingBalService;
	
	@Autowired
	private CustomerMasterService customerMasterService;
	
	@Autowired
	private HeadMasterService headMasterService;

	@GetMapping("/")
	public ResponseEntity<List<OpeningBal>> getAllOpeningBalData() {
		List<OpeningBal> openingBal = openingBalService.getAllData();
		return new ResponseEntity<List<OpeningBal>>(openingBal, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OpeningBal> getByOpeningBalIdData(@PathVariable long id) {
		OpeningBal openingBal = openingBalService.getByIdData(id);

		return new ResponseEntity<OpeningBal>(openingBal, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<OpeningBal> saveOpeningBalData(@RequestBody OpeningBalDto openingBalDto)
	{
		OpeningBal openingBal=new OpeningBal();
		
		openingBal.setCustId(customerMasterService.getById(openingBalDto.getCustId()));
		openingBal.setDate(openingBalDto.getDate());
		openingBal.setCrAmt(openingBalDto.getCrAmt());
		openingBal.setDrAmt(openingBalDto.getDrAmt());
		openingBal.setAmount(openingBalDto.getAmount());
		openingBal.setYear(openingBalDto.getYear());
		openingBal.setHeadId(headMasterService.getById(openingBalDto.getHeadId()));
		
		OpeningBal saveOpeningBal=openingBalService.postData(openingBal);
		
		return new ResponseEntity<OpeningBal>(saveOpeningBal,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OpeningBal> putOpeningBalData(@RequestBody OpeningBalDto openingBalDto,@PathVariable long id)
	{
		OpeningBal openingBal=openingBalService.getByIdData(id);
		if(openingBal != null)
		{
			openingBal.setCustId(customerMasterService.getById(openingBalDto.getCustId()));
			openingBal.setDate(openingBalDto.getDate());
			openingBal.setCrAmt(openingBalDto.getCrAmt());
			openingBal.setDrAmt(openingBalDto.getDrAmt());
			openingBal.setAmount(openingBalDto.getAmount());
			openingBal.setYear(openingBalDto.getYear());
			openingBal.setHeadId(headMasterService.getById(openingBalDto.getHeadId()));
			
			OpeningBal saveOpeningBal=openingBalService.postData(openingBal);
			
			return new ResponseEntity<OpeningBal>(saveOpeningBal,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<OpeningBal>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<OpeningBal> deleteOpeningBalData(@PathVariable long id)
	{
		openingBalService.deleteData(id);
		
		return new ResponseEntity<OpeningBal>(HttpStatus.OK);
	}

}
