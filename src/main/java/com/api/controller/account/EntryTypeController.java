package com.api.controller.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.account.EntryTypeDto;
import com.api.entity.School;
import com.api.entity.account.EntryType;
import com.api.service.SchoolService;
import com.api.service.account.EntryTypeService;

@RestController
@RequestMapping("/entrytype")
public class EntryTypeController {
	
	@Autowired
	private EntryTypeService entryTypeService;
	
	@Autowired
	private SchoolService schoolService;
	
	@PostMapping("/")
	public ResponseEntity<EntryType> savealldata(@RequestBody EntryTypeDto entryTypeDto){
		
		EntryType entryType = new EntryType();
		School school = schoolService.getbyid(entryTypeDto.getUdiseNo());
		if(school != null ) {
			
			entryType.setName(entryTypeDto.getName());
			entryType.setSchoolUdiseNo(school);
			EntryType savedata = entryTypeService.post(entryType);
		
			return new ResponseEntity<EntryType>(savedata, HttpStatus.OK);
		}else {
			return new ResponseEntity<EntryType>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EntryType> putbyid(@PathVariable int id, @RequestBody EntryTypeDto entryTypeDto){
		EntryType entryType=entryTypeService.getById(id);
		School school = schoolService.getbyid(entryTypeDto.getUdiseNo());
		if(entryType != null && school != null) {
			entryType.setName(entryTypeDto.getName());
			entryType.setSchoolUdiseNo(school);
			
			EntryType saveddata=entryTypeService.post(entryType);
			
			return new ResponseEntity<EntryType>(saveddata, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<EntryType>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<EntryType>> getalldata(){
		List<EntryType> entryType=entryTypeService.getdata();
		
		return new ResponseEntity<List<EntryType>>(entryType, HttpStatus.OK);
	}
	
	@GetMapping("/{udiseNo}")
	public ResponseEntity<List<EntryType>> getdatabyudise(@PathVariable long udiseNo){
		List<EntryType> entryType=entryTypeService.getdatabyudiseno(udiseNo);
		return new ResponseEntity<List<EntryType>>(entryType, HttpStatus.OK);
	}

}
