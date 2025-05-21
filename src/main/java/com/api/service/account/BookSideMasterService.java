package com.api.service.account;

import java.util.List;

import com.api.entity.account.BookSideMaster;

public interface BookSideMasterService {
	
	public List<BookSideMaster> getAllData();
	
	public BookSideMaster getById(long id);
	
	public BookSideMaster postData(BookSideMaster bookSideMaster);
	
	public void deleteData(long id);
	
}
