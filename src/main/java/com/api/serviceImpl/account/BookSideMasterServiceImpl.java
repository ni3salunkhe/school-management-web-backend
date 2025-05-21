package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.entity.account.BookSideMaster;
import com.api.repository.account.BookSideMasterRepository;
import com.api.service.account.BookSideMasterService;

public class BookSideMasterServiceImpl implements BookSideMasterService{

	@Autowired
	private BookSideMasterRepository bookSideMasterRepository;
	
	@Override
	public List<BookSideMaster> getAllData() {
		// TODO Auto-generated method stub
		return bookSideMasterRepository.findAll();
	}

	@Override
	public BookSideMaster getById(long id) {
		// TODO Auto-generated method stub
		return bookSideMasterRepository.findById(id).orElse(null);
	}

	@Override
	public BookSideMaster postData(BookSideMaster bookSideMaster) {
		// TODO Auto-generated method stub
		return bookSideMasterRepository.save(bookSideMaster);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		bookSideMasterRepository.deleteById(id);
	}

}
