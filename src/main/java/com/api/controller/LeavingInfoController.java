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

import com.api.dto.LeavingInfoDto;
import com.api.entity.LeavingInfo;

import com.api.service.LeavingInfoService;
import com.api.service.StudentService;

@RestController
@RequestMapping("/leavinginfo")
public class LeavingInfoController {
	
	@Autowired
	private LeavingInfoService leavingInfoService;
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/")
	public ResponseEntity<LeavingInfo> savedata(@RequestBody LeavingInfoDto leavingInfoDto)
	{
		LeavingInfo leavingInfo=new LeavingInfo();
		leavingInfo.setStudentRegisterNo(studentService.getbyid(leavingInfoDto.getStudentRegisterNo()));
		leavingInfo.setProgress(leavingInfoDto.getProgress());
		leavingInfo.setBehavior(leavingInfoDto.getBehavior());
		leavingInfo.setDateOfLeavingSchool(leavingInfoDto.getDateOfLeavingSchool());
		leavingInfo.setReasonOfLeavingSchool(leavingInfoDto.getReasonOfLeavingSchool());
		leavingInfo.setRemark(leavingInfoDto.getRemark());
		leavingInfo.setLcNumber(leavingInfoDto.getLcNumber());
		leavingInfo.setLcDate(leavingInfoDto.getLcDate());
		leavingInfo.setOtherRemark(leavingInfoDto.getOtherRemark());
		leavingInfo.setCreatedAt(leavingInfoDto.getCreatedAt());

		LeavingInfo saveLeavingInfo=leavingInfoService.post(leavingInfo);
		
		return new ResponseEntity<LeavingInfo>(saveLeavingInfo,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<LeavingInfo>> getdata()
	{
		List<LeavingInfo> leavingInfo=leavingInfoService.getdata();
		return new ResponseEntity<List<LeavingInfo>>(leavingInfo,HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<LeavingInfo> getbyidData(@PathVariable long id)
	{
		LeavingInfo leavingInfo=leavingInfoService.getbyid(id);
		
		return new ResponseEntity<LeavingInfo>(leavingInfo,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LeavingInfo> editdata(@PathVariable long id,@RequestBody LeavingInfoDto leavingInfoDto)
	{
		LeavingInfo leavingInfo=leavingInfoService.getbyid(id);
		
		if(leavingInfo==null)
		{
			return new ResponseEntity<LeavingInfo>(HttpStatus.NOT_FOUND);
		}
		else {
			leavingInfo.setStudentRegisterNo(studentService.getbyid(leavingInfoDto.getStudentRegisterNo()));
			leavingInfo.setProgress(leavingInfoDto.getProgress());
			leavingInfo.setBehavior(leavingInfoDto.getBehavior());
			leavingInfo.setDateOfLeavingSchool(leavingInfoDto.getDateOfLeavingSchool());
			leavingInfo.setReasonOfLeavingSchool(leavingInfoDto.getReasonOfLeavingSchool());
			leavingInfo.setRemark(leavingInfoDto.getRemark());
			leavingInfo.setLcNumber(leavingInfoDto.getLcNumber());
			leavingInfo.setLcDate(leavingInfoDto.getLcDate());
			leavingInfo.setOtherRemark(leavingInfoDto.getOtherRemark());
			leavingInfo.setCreatedAt(leavingInfoDto.getCreatedAt());

			LeavingInfo saveLeavingInfo=leavingInfoService.post(leavingInfo);
			
			return new ResponseEntity<LeavingInfo>(saveLeavingInfo,HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletedat(@PathVariable long id)
	{
		leavingInfoService.deletedata(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
