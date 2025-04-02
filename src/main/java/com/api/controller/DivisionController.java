package com.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.DivisionDto;
import com.api.entity.Division;
import com.api.service.DivisionService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/Division")
public class DivisionController {
	
	@Autowired
	private DivisionService divisionService;
	
//	@PostConstruct
//	public void initializeDivisions()
//	{	
//		 List<String> defaultDivisions = Arrays.asList("A", "B", "C", "D");
//		 for (String division : defaultDivisions)
//	}
	@PostMapping("/")
	public ResponseEntity<Division> savedata(@RequestBody DivisionDto divisionDto){
		return null;
	}
	
}
