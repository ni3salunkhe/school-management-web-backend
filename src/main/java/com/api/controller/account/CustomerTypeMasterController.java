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

import com.api.dto.account.CustomerTypeMasterDto;
import com.api.entity.account.CustomerTypeMaster;
import com.api.service.account.CustomerTypeMasterService;

@RestController
@RequestMapping("/customertype")
public class CustomerTypeMasterController {

	@Autowired
	private CustomerTypeMasterService customerTypeMasterService;

	@GetMapping("/")
	public ResponseEntity<List<CustomerTypeMaster>> getAllCustomerTypeMaster() {
		List<CustomerTypeMaster> customerTypeMasters = customerTypeMasterService.allData();
		return new ResponseEntity<List<CustomerTypeMaster>>(customerTypeMasters, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerTypeMaster> getCustomerTypeMasterDataById(@PathVariable long id) {
		CustomerTypeMaster customerTypeMaster = customerTypeMasterService.getById(id);
		return new ResponseEntity<CustomerTypeMaster>(customerTypeMaster, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<CustomerTypeMaster> saveCustomerTypeMasterData(@RequestBody CustomerTypeMasterDto customerTypeMasterDto){
		
		CustomerTypeMaster customerTypeMaster=new CustomerTypeMaster();
		
		customerTypeMaster.setCustomerTypeName(customerTypeMasterDto.getCustomerTypeName());
		CustomerTypeMaster saveCustomerTypeMaster=customerTypeMasterService.postData(customerTypeMaster);
		return new ResponseEntity<CustomerTypeMaster>(saveCustomerTypeMaster,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerTypeMaster> editCustomerTypeMasterData(@RequestBody CustomerTypeMasterDto customerTypeMasterDto ,@PathVariable long id){
		
		CustomerTypeMaster customerTypeMaster=customerTypeMasterService.getById(id);
		
		if(customerTypeMaster!=null)
		{
			customerTypeMaster.setCustomerTypeName(customerTypeMasterDto.getCustomerTypeName());
			CustomerTypeMaster saveCustomerTypeMaster=customerTypeMasterService.postData(customerTypeMaster);
			return new ResponseEntity<CustomerTypeMaster>(saveCustomerTypeMaster,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<CustomerTypeMaster>(HttpStatus.NOT_FOUND);
		}
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteData(@PathVariable long id)
	{
		customerTypeMasterService.deleteData(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	

}
