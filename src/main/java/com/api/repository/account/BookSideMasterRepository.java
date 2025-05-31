package com.api.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.account.BookSideMaster;

@Repository
public interface BookSideMasterRepository extends JpaRepository<BookSideMaster, Long>{
	
	public BookSideMaster findByBooksideName(String bookSideName);
	
}
