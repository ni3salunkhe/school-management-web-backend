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

import com.api.dto.account.BookSideMasterDto;
import com.api.entity.account.BookSideMaster;
import com.api.service.SchoolService;
import com.api.service.account.BookSideMasterService;
import com.api.service.account.BookTypeMasterService;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class BookSideMasterController {

	@Autowired
	private BookSideMasterService bookSideMasterService;

	@Autowired
	private BookTypeMasterService bookTypeMasterService;

	@Autowired
	private SchoolService schoolService;

	@GetMapping("/")
	public ResponseEntity<List<BookSideMaster>> getAllBookSideMasterData() {
		List<BookSideMaster> bookSideMasters = bookSideMasterService.getAllData();

		return new ResponseEntity<List<BookSideMaster>>(bookSideMasters, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookSideMaster> getBookSideMasterDataById(@PathVariable long id) {
		BookSideMaster bookSideMaster = bookSideMasterService.getById(id);

		return new ResponseEntity<BookSideMaster>(bookSideMaster, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<BookSideMaster> saveBookSideMasterData(@RequestBody BookSideMasterDto bookSideMasterDto) {
		BookSideMaster bookSideMaster = new BookSideMaster();

		bookSideMaster.setBooksideName(bookSideMasterDto.getBooksideName());
		bookSideMaster.setBooktypeId(bookTypeMasterService.getById(bookSideMasterDto.getBooktypeId()));
		bookSideMaster.setSchoolUdise(schoolService.getbyid(bookSideMasterDto.getSchoolUdise()));
		bookSideMaster.setEntryDate(bookSideMasterDto.getEntryDate());

		BookSideMaster saveBookSideMaster = bookSideMasterService.postData(bookSideMaster);

		return new ResponseEntity<BookSideMaster>(saveBookSideMaster, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookSideMaster> editBookSideMasterData(@RequestBody BookSideMasterDto bookSideMasterDto,@PathVariable long id) {
		BookSideMaster bookSideMaster = bookSideMasterService.getById(id);

		if (bookSideMaster != null) {
			bookSideMaster.setBooksideName(bookSideMasterDto.getBooksideName());
			bookSideMaster.setBooktypeId(bookTypeMasterService.getById(bookSideMasterDto.getBooktypeId()));
			bookSideMaster.setSchoolUdise(schoolService.getbyid(bookSideMasterDto.getSchoolUdise()));
			bookSideMaster.setEntryDate(bookSideMasterDto.getEntryDate());

			BookSideMaster saveBookSideMaster = bookSideMasterService.postData(bookSideMaster);

			return new ResponseEntity<BookSideMaster>(saveBookSideMaster, HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<BookSideMaster>(HttpStatus.NOT_FOUND);
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBookSideMasterData(@PathVariable long id)
	{
		bookSideMasterService.deleteData(id);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
