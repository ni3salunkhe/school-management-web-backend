package com.api.controller;

import java.io.IOException;
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
import com.api.service.DeveloperService;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

	@Autowired
	private DeveloperService developerService;

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

}
