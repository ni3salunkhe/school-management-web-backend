package com.api.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.dto.DeveloperDto;
import com.api.entity.Developer;
import com.api.repository.DeveloperRepository;
import com.api.service.DeveloperService;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private DeveloperRepository devRepo;
	
	 @GetMapping("/active")
	    public ResponseEntity<Boolean> getActiveDevelopers() {
		 
		 if(devRepo.findByActiveTrue() != null)
		 {
			 return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		 }else {
			 return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		 }
	  }

	@PutMapping(value = "/{username}", consumes = "multipart/form-data")
	public ResponseEntity<Developer> editMobileNumber(@PathVariable String username,
			@RequestPart("developer") DeveloperDto developerDto, @RequestPart("qrCode") MultipartFile qrCode) {
		try {
			Developer developer = developerService.getByUsername(username);
			developer.setPhone(developerDto.getPhone());
			developer.setQrCode(qrCode.getBytes());

			Developer editDeveloper = developerService.saveData(developer);
			return new ResponseEntity<>(editDeveloper, HttpStatus.OK);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{username}")
	public ResponseEntity<Developer> getByUserName(@PathVariable String username) {
		Developer developer = developerService.getByUsername(username);

		return new ResponseEntity<>(developer, HttpStatus.OK);
	}

	@GetMapping("/getPhoneOrQr/{id}")
	public ResponseEntity<Map<String, Object>> getPhoneAndQr(@PathVariable long id) {
		Developer developer = developerService.getById(id);

		if (developer == null) {
			return ResponseEntity.notFound().build();
		}

		Map<String, Object> response = new HashMap<>();
		response.put("phone", developer.getPhone());

		if (developer.getQrCode() != null) {
			String base64Qr = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(developer.getQrCode());
			response.put("qrCode", base64Qr);
		}

		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/renew/{id}/{date}")
	public ResponseEntity<?> renew(@PathVariable Long id, @PathVariable LocalDate date) {
	    Developer dev = devRepo.findById(id).orElseThrow();
	    dev.setAccountExpiryDate(date.plusYears(1));
	    dev.setActive(true);
	    devRepo.save(dev);
	    return ResponseEntity.ok("Renewed for 1 year");
	}
	
	@GetMapping("/expiry-date-by-name/{username}")
	public ResponseEntity<?> getByUsername(@PathVariable String username) {
		Developer developer = developerService.getByUsername(username);
		LocalDate date = developer.getAccountExpiryDate();
		return new ResponseEntity<>(date, HttpStatus.OK);
	}


}
