package com.api.service.account;

import java.util.List;

import com.api.entity.account.BookTypeMaster;

public interface BookTypeMasterService {
	
	public List<BookTypeMaster> getAllData();
	
	public BookTypeMaster getById(long id);
	
	public BookTypeMaster postData(BookTypeMaster bookTypeMaster);
	
	public void deleteData(long id);
	
}
