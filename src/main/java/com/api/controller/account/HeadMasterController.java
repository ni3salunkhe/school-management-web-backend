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

import com.api.dto.account.HeadMasterDto;
import com.api.entity.account.HeadMaster;
import com.api.service.SchoolService;
import com.api.service.account.BookSideMasterService;
import com.api.service.account.BookTypeMasterService;
import com.api.service.account.HeadMasterService;

@RestController
@RequestMapping("/headmaster")
public class HeadMasterController {

	@Autowired
	private HeadMasterService headMasterService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private BookSideMasterService bookSideMasterService;
	
	@Autowired 
	private BookTypeMasterService bookTypeMasterService;

	@GetMapping("/")
	public ResponseEntity<List<HeadMaster>> getAllHeadMaster() {
		List<HeadMaster> headMasters = headMasterService.getAllData();

		return new ResponseEntity<List<HeadMaster>>(headMasters, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<HeadMaster> getByIdHeadMaster(@PathVariable long id) {
		HeadMaster headMaster = headMasterService.getById(id);

		return new ResponseEntity<HeadMaster>(headMaster, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<HeadMaster> saveHeadMasterData(@RequestBody HeadMasterDto headMasterDto) {
		HeadMaster headMaster = new HeadMaster();

		headMaster.setHeadId(headMasterDto.getHeadId());
		headMaster.setSchoolUdise(schoolService.getbyid(headMasterDto.getSchoolUdise()));
		headMaster.setHead_name(headMasterDto.getHead_name());
		headMaster.setBookSideMaster(bookSideMasterService.getById(headMasterDto.getBookSideMaster()));
		headMaster.setBookTypeMaster(bookTypeMasterService.getById(headMasterDto.getBookTypeMaster()));
		
		HeadMaster saveHeadMaster = headMasterService.postData(headMaster);
		return new ResponseEntity<HeadMaster>(saveHeadMaster, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<HeadMaster> putHeadMasterData(@PathVariable long id,
			@RequestBody HeadMasterDto headMasterDto) {
		HeadMaster headMaster = headMasterService.getById(id);

		if (headMaster != null) {

			headMaster.setHeadId(headMasterDto.getHeadId());
			headMaster.setHead_name(headMasterDto.getHead_name());
			headMaster.setBookSideMaster(bookSideMasterService.getById(headMasterDto.getBookSideMaster()));
			headMaster.setBookTypeMaster(bookTypeMasterService.getById(headMasterDto.getBookTypeMaster()));
			
			HeadMaster saveHeadMaster = headMasterService.postData(headMaster);

			return new ResponseEntity<HeadMaster>(saveHeadMaster, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<HeadMaster>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getbyudise/{udiseNo}")
	public ResponseEntity<List<HeadMaster>> getbyudiseno(@PathVariable long udiseNo){
		List<HeadMaster> headMaster=headMasterService.getByUdiseNo(udiseNo);
		return new ResponseEntity<List<HeadMaster>>(headMaster,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteHeadMasterData(@PathVariable long id) {
		headMasterService.deleteData(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
