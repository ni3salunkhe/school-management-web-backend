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

import com.api.dto.BonafideDto;
import com.api.entity.Bonafide;
import com.api.service.BonafideService;
import com.api.service.SchoolService;

@RestController
@RequestMapping("/bonafide")
public class BonafideController {
	
	@Autowired
	private BonafideService bonafideService;
	
	@Autowired
	private SchoolService schoolService;
	
	@GetMapping("/")
	public ResponseEntity<List<Bonafide>> getAllData()
	{
		List<Bonafide> bonafide=bonafideService.getAllData();
		return new ResponseEntity<List<Bonafide>>(bonafide,HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Bonafide> seveData(@RequestBody BonafideDto bonafideDto)
	{
		Bonafide bonafide=new Bonafide();
		bonafide.setLocation(bonafideDto.getLocation());
		bonafide.setReason(bonafideDto.getReason());
		bonafide.setSchool(schoolService.getbyid(bonafideDto.getSchool()));
		
		Bonafide saveBonafide=bonafideService.postData(bonafide);
		
		return new ResponseEntity<Bonafide>(saveBonafide,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Bonafide> putData(@PathVariable long id,@RequestBody BonafideDto bonafideDto)
	{
		Bonafide bonafide=bonafideService.getbyid(id);
		
		bonafide.setLocation(bonafideDto.getLocation());
		bonafide.setReason(bonafideDto.getReason());
		bonafide.setSchool(schoolService.getbyid(bonafideDto.getSchool()));
		
		Bonafide saveBonafide=bonafideService.postData(bonafide);
		
		return new ResponseEntity<Bonafide>(saveBonafide,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Bonafide> getbyid(@PathVariable long id)
	{
		Bonafide bonafide=bonafideService.getbyid(id);
		
		return new ResponseEntity<Bonafide>(bonafide,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDatabyid(@PathVariable long id)
	{
		bonafideService.deleteData(id);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
