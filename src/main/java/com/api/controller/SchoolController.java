package com.api.controller;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SchoolDto;
import com.api.entity.School;
import com.api.entity.account.CustomerMaster;
import com.api.entity.account.HeadMaster;
import com.api.entity.account.SubHeadMaster;
import com.api.repository.account.SubHeadMasterRepository;
import com.api.service.DistrictService;
import com.api.service.SchoolService;
import com.api.service.StateService;
import com.api.service.TehsilService;
import com.api.service.VillageService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.HeadMasterService;
import com.api.service.account.SubHeadMasterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/school")
public class SchoolController {

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private StateService stateService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private TehsilService tehsilService;

	@Autowired
	private VillageService villageService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private HeadMasterService headMasterService;

	@Autowired
	private CustomerMasterService customerMasterService;

	@Autowired
	private SubHeadMasterService subHeadMasterService;

	@Autowired
	private SubHeadMasterRepository subHeadMasterRepository;

	@PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<School> savedata(@RequestPart("schoolDto") String schoolDtoJson,
			@RequestPart(value = "logo", required = false) MultipartFile logoFile) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			SchoolDto schoolDto = objectMapper.readValue(schoolDtoJson, SchoolDto.class);
			// If logo file was sent separately, set it in the DTO
			if (logoFile != null && !logoFile.isEmpty()) {
				schoolDto.setLogo(logoFile);
			}

			School school = new School();
			school.setUdiseNo(schoolDto.getUdiseNo());
			school.setSchoolSlogan(schoolDto.getSchoolSlogan());
			school.setSansthaName(schoolDto.getSansthaName());
			school.setSchoolName(schoolDto.getSchoolName());
			school.setTehsil(tehsilService.getbyid(schoolDto.getTehsil()));
			school.setDistrict(districtService.getbyid(schoolDto.getDistrict()));
			school.setState(stateService.getbyid(schoolDto.getState()));
			school.setVillage(villageService.getbyid(schoolDto.getVillage()));
			school.setPinCode(schoolDto.getPinCode());
			school.setMedium(schoolDto.getMedium());
			school.setHeadMasterUserName(schoolDto.getHeadMasterUserName());
			school.setHeadMasterMobileNo(schoolDto.getHeadMasterMobileNo());
			school.setHeadMasterEmailId(schoolDto.getHeadMasterEmailId());
			school.setHeadMasterPassword(passwordEncoder.encode(schoolDto.getHeadMasterPassword()));
			school.setBoard(schoolDto.getBoard());
			school.setBoardDivision(schoolDto.getBoardDivision());
			school.setBoardIndexNo(schoolDto.getBoardIndexNo());
			school.setSchoolEmailId(schoolDto.getSchoolEmailId());
			school.setSchoolApprovalNo(schoolDto.getSchoolApprovalNo());
			school.setRole(schoolDto.getRole());

			// Handle logo content
			if (schoolDto.getLogo() != null && !schoolDto.getLogo().isEmpty()) {
				MultipartFile logo = schoolDto.getLogo();
				if (!logo.getContentType().startsWith("image/")) {
					return new ResponseEntity<School>(HttpStatus.BAD_REQUEST);
				}
				school.setLogo(logo.getBytes());
			}

			school.setCreatedAt(schoolDto.getCreatedAt());

			School saveSchool = schoolService.post(school);

			HeadMaster headMaster = headMasterService.getByHeadName("Cash In Hand");

			HeadMaster profitHeadMaster = headMasterService.getByHeadName("Profit & Loss");

			CustomerMaster customerMaster = new CustomerMaster();
			customerMaster.setSchoolUdise(saveSchool);
			customerMaster.setCustName("Cash In Hand");
			customerMaster.setHeadId(headMaster);

			CustomerMaster saveCustomerMaster = customerMasterService.postData(customerMaster);

			SubHeadMaster subHeadMaster = new SubHeadMaster();
			subHeadMaster.setHeadId(headMaster);
			subHeadMaster.setSchoolUdise(saveSchool);
			subHeadMaster.setSubheadId(saveCustomerMaster.getCustId());
			subHeadMaster.setSubheadName("Cash In Hand");
			SubHeadMaster saveSubHeadMaster = subHeadMasterService.postData(subHeadMaster);

			saveCustomerMaster.setSubheadId(saveSubHeadMaster);

			customerMasterService.postData(saveCustomerMaster);

//			-----------------------------------------------------------------------

			long id = subHeadMasterService.getNextLedgerId();

			SubHeadMaster currentPeriodSubHeadMaster = new SubHeadMaster();
			currentPeriodSubHeadMaster.setSubheadId(id);
			currentPeriodSubHeadMaster.setHeadId(profitHeadMaster);
			currentPeriodSubHeadMaster.setSchoolUdise(saveSchool);
			currentPeriodSubHeadMaster.setSubheadName("Current Period");
			subHeadMasterService.postData(currentPeriodSubHeadMaster);

			long id2 = subHeadMasterService.getNextLedgerId();
			SubHeadMaster openingBalanceSubHeadMaster = new SubHeadMaster();
			openingBalanceSubHeadMaster.setSubheadId(id2);
			openingBalanceSubHeadMaster.setHeadId(profitHeadMaster);
			openingBalanceSubHeadMaster.setSchoolUdise(saveSchool);
			openingBalanceSubHeadMaster.setSubheadName("Opening Balance");
			SubHeadMaster saveopeningBalanceSubHeadMaster = subHeadMasterService.postData(openingBalanceSubHeadMaster);

			return new ResponseEntity<School>(saveSchool, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<School>> getdata() {
		List<School> school = schoolService.getdata();
		return new ResponseEntity<List<School>>(school, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<School> getbyid(@PathVariable long id) {
		School school = schoolService.getbyid(id);

		return new ResponseEntity<School>(school, HttpStatus.OK);
	}

	@PostMapping("/checkmobileamdemail")
	public ResponseEntity<Map<String, Object>> checkmobileamdemail(@RequestBody SchoolDto schoolDto) {
		School school = schoolService.getByHeadMasterEmailAndMobileNo(schoolDto.getHeadMasterEmailId(),
				schoolDto.getHeadMasterMobileNo());

		if (school != null) {
			Map<String, Object> response = new HashMap<>();
			response.put("id", school.getUdiseNo());
			response.put("Available", true);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		else {
			Map<String, Object> error = new HashMap<>();
			error.put("Available", false);
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/resetpassword/{id}")
	public ResponseEntity<School> resetHeadMasterPassword(@RequestBody SchoolDto schoolDto, @PathVariable long id) {
		School school = schoolService.getbyid(id);
		school.setHeadMasterPassword(schoolDto.getHeadMasterPassword());

		School saveSchool = schoolService.post(school);
		return new ResponseEntity<School>(saveSchool, HttpStatus.OK);

	}

	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<School> editdata(@PathVariable long id, @RequestPart("schoolDto") String schoolDtoJson,
			@RequestPart(value = "logo", required = false) MultipartFile logoFile) {
		try {
			School existingSchool = schoolService.getbyid(id);
			if (existingSchool == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			ObjectMapper objectMapper = new ObjectMapper();
			SchoolDto schoolDto = objectMapper.readValue(schoolDtoJson, SchoolDto.class);

			if (logoFile != null && !logoFile.isEmpty()) {
				schoolDto.setLogo(logoFile);
			}

			existingSchool.setUdiseNo(schoolDto.getUdiseNo());
			existingSchool.setSchoolSlogan(schoolDto.getSchoolSlogan());
			existingSchool.setSansthaName(schoolDto.getSansthaName());
			existingSchool.setSchoolName(schoolDto.getSchoolName());
			existingSchool.setTehsil(tehsilService.getbyid(schoolDto.getTehsil()));
			existingSchool.setDistrict(districtService.getbyid(schoolDto.getDistrict()));
			existingSchool.setState(stateService.getbyid(schoolDto.getState()));
			existingSchool.setVillage(villageService.getbyid(schoolDto.getVillage()));
			existingSchool.setPinCode(schoolDto.getPinCode());
			existingSchool.setMedium(schoolDto.getMedium());
			existingSchool.setHeadMasterMobileNo(schoolDto.getHeadMasterMobileNo());
			existingSchool.setHeadMasterPassword(schoolDto.getHeadMasterPassword());
			existingSchool.setHeadMasterEmailId(schoolDto.getHeadMasterEmailId());
			existingSchool.setBoard(schoolDto.getBoard());
			existingSchool.setBoardDivision(schoolDto.getBoardDivision());
			existingSchool.setBoardIndexNo(schoolDto.getBoardIndexNo());
//	        existingSchool.setSchoolEmailId(schoolDto.getSchoolEmailId());
			existingSchool.setSchoolApprovalNo(schoolDto.getSchoolApprovalNo());
			existingSchool.setRole(schoolDto.getRole());

			if (schoolDto.getLogo() != null && !schoolDto.getLogo().isEmpty()) {
				MultipartFile logo = schoolDto.getLogo();
				if (!logo.getContentType().startsWith("image/")) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				existingSchool.setLogo(logo.getBytes());
			}

			existingSchool.setCreatedAt(schoolDto.getCreatedAt());

			School updatedSchool = schoolService.post(existingSchool);
			return new ResponseEntity<>(updatedSchool, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<School> deletedata(@PathVariable long id) {
		schoolService.deletedata(id);
		return new ResponseEntity<School>(HttpStatus.OK);
	}
}
