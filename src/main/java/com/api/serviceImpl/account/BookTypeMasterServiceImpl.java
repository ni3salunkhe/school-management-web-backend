package com.api.serviceImpl.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.account.BookTypeMaster;
import com.api.repository.account.BookTypeMasterRepository;
import com.api.service.account.BookTypeMasterService;

@Service
public class BookTypeMasterServiceImpl implements BookTypeMasterService{
	
	@Autowired
	private BookTypeMasterRepository bookTypeMasterRepository;

	@Override
	public List<BookTypeMaster> getAllData() {
		// TODO Auto-generated method stub
		return bookTypeMasterRepository.findAll();
	}

	@Override
	public BookTypeMaster getById(long id) {
		// TODO Auto-generated method stub
		return bookTypeMasterRepository.findById(id).orElse(null);
	}

	@Override
	public BookTypeMaster postData(BookTypeMaster bookTypeMaster) {
		// TODO Auto-generated method stub
		return bookTypeMasterRepository.save(bookTypeMaster);
	}

	@Override
	public void deleteData(long id) {
		// TODO Auto-generated method stub
		bookTypeMasterRepository.deleteById(id);
	}

	@Override
	public BookTypeMaster getByBookTypeName(String bookTypeName) {
		// TODO Auto-generated method stub
		return bookTypeMasterRepository.findByBooktypeName(bookTypeName);
	}

}
