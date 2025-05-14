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

import com.api.dto.ModuleMasterDto;
import com.api.entity.ModuleMaster;
import com.api.service.ModuleMasterService;

@RestController
@RequestMapping("/modulemaster")
public class ModuleMasterController {

	@Autowired
	private ModuleMasterService moduleMasterService;

	@GetMapping("/")
	public ResponseEntity<List<ModuleMaster>> getAllDatas() {
		List<ModuleMaster> moduleMasters = moduleMasterService.getAllData();

		return new ResponseEntity<List<ModuleMaster>>(moduleMasters, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ModuleMaster> getByIDData(@PathVariable long id) {
		ModuleMaster moduleMaster = moduleMasterService.getById(id);

		return new ResponseEntity<ModuleMaster>(moduleMaster, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ModuleMaster> saveData(@RequestBody ModuleMasterDto moduleMasterDto) {
		ModuleMaster moduleMaster = new ModuleMaster();
		moduleMaster.setModuleCode(moduleMasterDto.getModuleCode());
		moduleMaster.setModuleName(moduleMasterDto.getModuleName());

		ModuleMaster saveModuleMaster = moduleMasterService.post(moduleMaster);

		return new ResponseEntity<ModuleMaster>(saveModuleMaster, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ModuleMaster> putData(@PathVariable long id, @RequestBody ModuleMasterDto moduleMasterDto) {
		ModuleMaster moduleMaster = moduleMasterService.getById(id);
		if (moduleMaster != null) {
			moduleMaster.setModuleCode(moduleMasterDto.getModuleCode());
			moduleMaster.setModuleName(moduleMasterDto.getModuleName());
			ModuleMaster saveModuleMaster = moduleMasterService.post(moduleMaster);

			return new ResponseEntity<ModuleMaster>(saveModuleMaster, HttpStatus.OK);
		} else {
			return new ResponseEntity<ModuleMaster>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteData(@PathVariable long id)
	{
		moduleMasterService.deleteData(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
