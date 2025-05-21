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

import com.api.dto.account.SubHeadMasterDto;
import com.api.entity.account.SubHeadMaster;
import com.api.service.SchoolService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.SubHeadMasterService;

@RestController
@RequestMapping("/subheadmaster")
public class SubHeadMasterController {

	@Autowired
	private SubHeadMasterService subHeadMasterService;

	@Autowired
	private HeadMasterService headMasterService;
	
	@Autowired
	private SchoolService schoolService;

	@GetMapping("/")
	public ResponseEntity<List<SubHeadMaster>> getAllSubHeadMasterData() {
		List<SubHeadMaster> subHeadMasters = subHeadMasterService.getAllData();
		return new ResponseEntity<List<SubHeadMaster>>(subHeadMasters, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubHeadMaster> getBySubHeadMasterId(@PathVariable long id) {
		SubHeadMaster subHeadMaster = subHeadMasterService.getById(id);

		return new ResponseEntity<SubHeadMaster>(subHeadMaster, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<SubHeadMaster> saveSubHeadMasterData(@RequestBody SubHeadMasterDto subHeadMasterDto) {
		SubHeadMaster subHeadMaster = new SubHeadMaster();

		subHeadMaster.setSubHeadId(subHeadMasterDto.getSubHeadId());
		subHeadMaster.setSubHeadName(subHeadMasterDto.getSubHeadName());
		subHeadMaster.setHeadId(headMasterService.getById(subHeadMasterDto.getHeadId()));
		subHeadMaster.setSchoolUdise(schoolService.getbyid(subHeadMasterDto.getSchoolUdise()));

		SubHeadMaster saveHeadMaster = subHeadMasterService.postData(subHeadMaster);

		return new ResponseEntity<SubHeadMaster>(saveHeadMaster, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SubHeadMaster> putSubHeadMasterData(@PathVariable long id,
			@RequestBody SubHeadMasterDto subHeadMasterDto) {
		SubHeadMaster subHeadMaster = subHeadMasterService.getById(id);

		if (subHeadMaster != null) {

			subHeadMaster.setSubHeadId(subHeadMasterDto.getSubHeadId());
			subHeadMaster.setSubHeadName(subHeadMasterDto.getSubHeadName());
			subHeadMaster.setHeadId(headMasterService.getById(subHeadMasterDto.getHeadId()));

			SubHeadMaster saveHeadMaster = subHeadMasterService.postData(subHeadMaster);

			return new ResponseEntity<SubHeadMaster>(saveHeadMaster, HttpStatus.OK);
		} else {
			return new ResponseEntity<SubHeadMaster>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSubHeadMasterData(@PathVariable long id)
	{
		headMasterService.deleteData(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
