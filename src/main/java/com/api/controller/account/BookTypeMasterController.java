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

import com.api.dto.account.BookTypeMasterDto;
import com.api.entity.account.BookTypeMaster;
import com.api.service.SchoolService;
import com.api.service.account.BookTypeMasterService;

@RestController
@RequestMapping("/booktypemaster")
public class BookTypeMasterController {

	@Autowired
	private BookTypeMasterService bookTypeMasterService;

	@Autowired
	private SchoolService schoolService;

	@GetMapping("/")
	public ResponseEntity<List<BookTypeMaster>> getAllBookTypeData() {
		List<BookTypeMaster> bookTypeMasters = bookTypeMasterService.getAllData();

		return new ResponseEntity<List<BookTypeMaster>>(bookTypeMasters, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookTypeMaster> getBookTypeDataById(@PathVariable long id) {
		BookTypeMaster bookTypeMaster = bookTypeMasterService.getById(id);

		return new ResponseEntity<BookTypeMaster>(bookTypeMaster, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<BookTypeMaster> saveBookTypeData(@RequestBody BookTypeMasterDto bookTypeMasterDto) {
		BookTypeMaster bookTypeMaster = new BookTypeMaster();

		bookTypeMaster.setBooktypeName(bookTypeMasterDto.getBooktypeName());
		bookTypeMaster.setEntryDate(bookTypeMasterDto.getEntryDate());
		bookTypeMaster.setSchool_udise(schoolService.getbyid(bookTypeMasterDto.getSchool_udise()));

		BookTypeMaster saveBookTypeMaster = bookTypeMasterService.postData(bookTypeMaster);

		return new ResponseEntity<BookTypeMaster>(saveBookTypeMaster, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookTypeMaster> editBookTypeData(@PathVariable long id,@RequestBody BookTypeMasterDto bookTypeMasterDto) {
		BookTypeMaster bookTypeMaster = bookTypeMasterService.getById(id);
		if (bookTypeMaster != null) {
			bookTypeMaster.setBooktypeName(bookTypeMasterDto.getBooktypeName());
			bookTypeMaster.setEntryDate(bookTypeMasterDto.getEntryDate());
			bookTypeMaster.setSchool_udise(schoolService.getbyid(bookTypeMasterDto.getSchool_udise()));

			BookTypeMaster saveBookTypeMaster = bookTypeMasterService.postData(bookTypeMaster);

			return new ResponseEntity<BookTypeMaster>(saveBookTypeMaster, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<BookTypeMaster>(HttpStatus.NOT_FOUND);
		}
		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBookTypeData(@PathVariable long id) {
		bookTypeMasterService.deleteData(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
