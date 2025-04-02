package com.api.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.dto.SchoolDto;
import com.api.entity.School;
import com.api.service.DistrictService;
import com.api.service.SchoolService;
import com.api.service.StateService;
import com.api.service.TehsilService;
import com.api.service.VillageService;

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
	
	@PostMapping("/")
	public ResponseEntity<School> savedata(@ModelAttribute  SchoolDto schoolDto)
	{
		try {
			School school=new School();
			school.setUdiseNo(schoolDto.getUdiseNo());
			school.setSchoolSlogan(schoolDto.getSchoolSlogan());
			school.setSansthaName(schoolDto.getSansthaName());
			school.setSchoolName(schoolDto.getSchoolName());
			school.setTehsil(tehsilService.getbyid (schoolDto.getTehsil()));
			school.setDistrict(districtService.getbyid( schoolDto.getDistrict()));
			school.setState(stateService.getbyid(schoolDto.getState()));
			school.setVillage(villageService.getbyid(schoolDto.getVillage()));
			school.setPinCode(schoolDto.getPinCode());
			school.setMedium(schoolDto.getMedium());
			school.setHeadMasterName(schoolDto.getHeadMasterName());
			school.setHeadMasterMobileNo(schoolDto.getHeadMasterMobileNo());
			school.setHeadMasterPassword(schoolDto.getHeadMasterPassword());
			school.setBoard(schoolDto.getBoard());
			school.setBoardDivision(schoolDto.getBoardDivision());
			school.setBoardIndexNo(schoolDto.getBoardIndexNo());
			school.setSchoolEmailId(schoolDto.getSchoolEmailId());
			school.setSchoolApprovalNo(schoolDto.getSchoolApprovalNo());
			
			
			if(schoolDto.getLogo() !=null && !schoolDto.getLogo().isEmpty())
			{
				MultipartFile logoFile=schoolDto.getLogo();
				 if (!logoFile.getContentType().startsWith("image/")) {
		             
					 return new ResponseEntity<School>(HttpStatus.BAD_REQUEST);
					 
		            }

				school.setLogo(logoFile.getBytes());
			}
			
			
			school.setCreatedAt(schoolDto.getCreatedAt());
			
			School saveSchool=schoolService.post(school);
			
			return new ResponseEntity<School>(saveSchool,HttpStatus.CREATED);
		
		} catch (Exception e) {
			 e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping("/")
	public ResponseEntity<List<School>> getdata()
	{
		List<School> school=schoolService.getdata();
		return new ResponseEntity<List<School>>(school,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<School> getbyid(@PathVariable long id)
	{
		School school=schoolService.getbyid(id);
		
		return new ResponseEntity<School>(school,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<School> editdata(@PathVariable long id,@RequestBody SchoolDto schoolDto)
	{
		School school=schoolService.getbyid(id);
		if(school==null)
		{
			return new ResponseEntity<School>(HttpStatus.NOT_FOUND);
		}
		else {
			try {
				
				school.setUdiseNo(schoolDto.getUdiseNo());
				school.setSchoolSlogan(schoolDto.getSchoolSlogan());
				school.setSansthaName(schoolDto.getSansthaName());
				school.setSchoolName(schoolDto.getSchoolName());
				school.setTehsil(tehsilService.getbyid (schoolDto.getTehsil()));
				school.setDistrict(districtService.getbyid( schoolDto.getDistrict()));
				school.setState(stateService.getbyid(schoolDto.getState()));
				school.setVillage(villageService.getbyid(schoolDto.getVillage()));
				school.setPinCode(schoolDto.getPinCode());
				school.setMedium(schoolDto.getMedium());
				school.setHeadMasterName(schoolDto.getHeadMasterName());
				school.setHeadMasterMobileNo(schoolDto.getHeadMasterMobileNo());
				school.setHeadMasterPassword(schoolDto.getHeadMasterPassword());
				school.setBoard(schoolDto.getBoard());
				school.setBoardDivision(schoolDto.getBoardDivision());
				school.setBoardIndexNo(schoolDto.getBoardIndexNo());
				school.setSchoolEmailId(schoolDto.getSchoolEmailId());
				school.setSchoolApprovalNo(schoolDto.getSchoolApprovalNo());
				
				
				if(schoolDto.getLogo() !=null && !schoolDto.getLogo().isEmpty())
				{
					MultipartFile logoFile=schoolDto.getLogo();
					school.setLogo(logoFile.getBytes());
				}
				
				
				school.setCreatedAt(schoolDto.getCreatedAt());
				
				School saveSchool=schoolService.post(school);
				
				return new ResponseEntity<School>(saveSchool,HttpStatus.CREATED);
			
			} catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<School> deletedata(@PathVariable long id)
	{
		schoolService.deletedata(id);
		return new ResponseEntity<School>(HttpStatus.OK);
	}
}
