package com.api.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.account.BookTypeMaster;

@Repository
public interface BookTypeMasterRepository extends JpaRepository<BookTypeMaster, Long>{
	
	public BookTypeMaster findByBooktypeName(String booktypeName);
	
}
