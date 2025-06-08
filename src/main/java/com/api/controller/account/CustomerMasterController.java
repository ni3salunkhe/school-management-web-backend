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

import com.api.dto.account.CustomerMasterDto;
import com.api.entity.School;
import com.api.entity.account.CustomerMaster;
import com.api.entity.account.SubHeadMaster;
import com.api.service.SchoolService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.CustomerTypeMasterService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.SubHeadMasterService;

@RestController
@RequestMapping("/customermaster")
public class CustomerMasterController {

	@Autowired
	private CustomerMasterService customerMasterService;

	@Autowired
	private HeadMasterService headMasterService;

	@Autowired
	private SubHeadMasterService subHeadMasterService;

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private CustomerTypeMasterService customerTypeMasterService;

	@GetMapping("/")
	public ResponseEntity<List<CustomerMaster>> getAllCustomerMasterData() {
		List<CustomerMaster> customerMaster = customerMasterService.getAllMasters();

		return new ResponseEntity<List<CustomerMaster>>(customerMaster, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerMaster> getByCustomerMasterIdData(@PathVariable long id) {
		CustomerMaster customerMaster = customerMasterService.getById(id);

		return new ResponseEntity<CustomerMaster>(customerMaster, HttpStatus.OK);
	}

	@GetMapping("/getbyudise/{udise}")
	public ResponseEntity<List<CustomerMaster>> getCustomerMasterByUdise(@PathVariable long udise) {
		School school = schoolService.getbyid(udise);

		List<CustomerMaster> customerMasters = customerMasterService.getByUdise(school);

		return new ResponseEntity<List<CustomerMaster>>(customerMasters, HttpStatus.OK);
	}

	@GetMapping("/getcustomersbyudise/{udise}")
	public ResponseEntity<List<CustomerMaster>> getCustomersByUdise(@PathVariable long udise) {

		List<CustomerMaster> customerMasters = customerMasterService.getonlycustomerbyUdise(udise);

		return new ResponseEntity<>(customerMasters, HttpStatus.OK);
	}

	@GetMapping("/getcustomerbyheadname/{headname}/{udise}")
	public ResponseEntity<List<CustomerMaster>> getCustomerByHeadName(@PathVariable String headname,@PathVariable long udise) {
		List<CustomerMaster> customerMasters = customerMasterService.getDataByHeadNameAndUdise(udise,headname);

		return new ResponseEntity<>(customerMasters, HttpStatus.OK);
	}
	
	@GetMapping("/byheadname/{udiseNo}/{headName}")
	public ResponseEntity<List<CustomerMaster>> getbyheadname(@PathVariable Long udiseNo, @PathVariable String headName){
		List<CustomerMaster> customerMaster = customerMasterService.getbyheadname(headName, udiseNo);
		return new ResponseEntity<List<CustomerMaster>>(customerMaster, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<CustomerMaster> saveCustomerMasterData(@RequestBody CustomerMasterDto customerMasterDto) {
		CustomerMaster customerMaster = new CustomerMaster();

		customerMaster.setCustName(customerMasterDto.getCustName());
		customerMaster.setCustAddress(customerMasterDto.getCustAddress());
		customerMaster.setContactPerson(customerMasterDto.getContactPerson());
		customerMaster.setCustMob1(customerMasterDto.getCustMob1());
		customerMaster.setCustMob2(customerMasterDto.getCustMob2());
		customerMaster.setCrAmt(customerMasterDto.getCrAmt());
		customerMaster.setDrAmt(customerMasterDto.getDrAmt());
		customerMaster.setHeadId(headMasterService.getById(customerMasterDto.getHeadId()));
		customerMaster.setCustTypeID(customerTypeMasterService.getById(customerMasterDto.getCustTypeID()));
		customerMaster.setEMail(customerMasterDto.getEMail());
		customerMaster.setGstin(customerMasterDto.getGstin());
		customerMaster.setPanNo(customerMasterDto.getPanNo());
		customerMaster.setPinCode(customerMasterDto.getPinCode());
		customerMaster.setStatus(customerMasterDto.getStatus());
		customerMaster.setSchoolUdise(schoolService.getbyid(customerMasterDto.getSchoolUdise()));

		CustomerMaster saveCustomerMaster = customerMasterService.postData(customerMaster);

		SubHeadMaster subHeadMaster = new SubHeadMaster();

		subHeadMaster.setSubheadId(saveCustomerMaster.getCustId());
		subHeadMaster.setHeadId(saveCustomerMaster.getHeadId());
		subHeadMaster.setSubheadName(saveCustomerMaster.getCustName());
		subHeadMaster.setSchoolUdise(saveCustomerMaster.getSchoolUdise());

		SubHeadMaster subHeadMaster2 = subHeadMasterService.postData(subHeadMaster);

		customerMaster.setSubheadId(subHeadMasterService.getById(subHeadMaster2.getSubheadId()));
		customerMasterService.postData(customerMaster);

		return new ResponseEntity<CustomerMaster>(saveCustomerMaster, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerMaster> putCustomerMasterData(@RequestBody CustomerMasterDto customerMasterDto,
			@PathVariable long id) {
		CustomerMaster customerMaster = customerMasterService.getById(id);

		if (customerMaster != null) {
			customerMaster.setCustName(customerMasterDto.getCustName());
			customerMaster.setCustAddress(customerMasterDto.getCustAddress());
			customerMaster.setContactPerson(customerMasterDto.getContactPerson());
			customerMaster.setCustMob1(customerMasterDto.getCustMob1());
			customerMaster.setCustMob2(customerMasterDto.getCustMob2());
			customerMaster.setCrAmt(customerMasterDto.getCrAmt());
			customerMaster.setDrAmt(customerMasterDto.getDrAmt());
			customerMaster.setHeadId(headMasterService.getById(customerMasterDto.getHeadId()));
			customerMaster.setSubheadId(subHeadMasterService.getById(customerMasterDto.getSubheadId()));
			customerMaster.setCustTypeID(customerTypeMasterService.getById(customerMasterDto.getCustTypeID()));
			customerMaster.setEMail(customerMasterDto.getEMail());
			customerMaster.setGstin(customerMasterDto.getGstin());
			customerMaster.setPanNo(customerMasterDto.getPanNo());
			customerMaster.setPinCode(customerMasterDto.getPinCode());
			customerMaster.setStatus(customerMasterDto.getStatus());
			customerMaster.setSchoolUdise(schoolService.getbyid(customerMasterDto.getSchoolUdise()));

			CustomerMaster saveCustomerMaster = customerMasterService.postData(customerMaster);

			return new ResponseEntity<CustomerMaster>(saveCustomerMaster, HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomerMaster>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteData(@PathVariable long id) {
		customerMasterService.deleteData(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
