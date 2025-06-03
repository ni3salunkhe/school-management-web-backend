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

import com.api.dto.account.YearMasterDto;
import com.api.entity.account.YearMaster;
import com.api.service.account.YearMasterService;

@RestController
@RequestMapping("/year")
public class YearMasterController {
	
	@Autowired
	private YearMasterService yearMasterService;
	
	@GetMapping("/")
	public ResponseEntity<List<YearMaster>> getYearMasterData()
	{
		List<YearMaster> yearMasters=yearMasterService.getAllData();
		
		return new ResponseEntity<>(yearMasters,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<YearMaster> getYearMasterById(long id)
	{
		YearMaster yearMaster=yearMasterService.getDataById(id);
		
		return new ResponseEntity<>(yearMaster,HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<YearMaster> saveYearMasterData(@RequestBody YearMasterDto yearMasterDto)
	{
		YearMaster yearMaster=new YearMaster();
		
		yearMaster.setYear(yearMasterDto.getYear());
		
		YearMaster saveYearMaster=yearMasterService.postData(yearMaster);
		
		return new ResponseEntity<>(saveYearMaster,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<YearMaster> editYearMasterData(@PathVariable long id,@RequestBody YearMasterDto yearMasterDto)
	{
		YearMaster yearMaster=yearMasterService.getDataById(id);
		
		if(yearMaster !=null)
		{
			yearMaster.setYear(yearMasterDto.getYear());
			
			YearMaster saveYearMaster=yearMasterService.postData(yearMaster);
			
			return new ResponseEntity<>(saveYearMaster,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteData(@PathVariable long id)
	{
		yearMasterService.deleteData(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
