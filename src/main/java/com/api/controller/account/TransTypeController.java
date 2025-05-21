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

import com.api.dto.account.TransTypeDto;
import com.api.entity.School;
import com.api.entity.account.TransType;
import com.api.service.SchoolService;
import com.api.service.account.TransTypeService;

@RestController
@RequestMapping("/transtype")
public class TransTypeController {
	
	@Autowired
	private TransTypeService transTypeService;
	
	@Autowired
	private SchoolService schoolService;
	
	@PostMapping("/")
	public ResponseEntity<TransType> savealldata(@RequestBody TransTypeDto transTypeDto){
		TransType transType=new TransType();
		School school = schoolService.getbyid(transTypeDto.getUdiseNo());
		if(school != null ) {
			transType.setName(transTypeDto.getName());
			transType.setSchoolUdiseNo(school);
			
			TransType saveTransData=transTypeService.post(transType);
			return new ResponseEntity<TransType>(saveTransData,HttpStatus.OK);
		}else {
			return new ResponseEntity<TransType>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/{udiseNo}")
	public ResponseEntity<List<TransType>> getdatabyudise(@PathVariable long udiseNo){
		List<TransType> transType = transTypeService.getbyudiseno(udiseNo);
		return new ResponseEntity<List<TransType>>(transType,HttpStatus.OK);
	}
}
