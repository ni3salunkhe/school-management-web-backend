package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.School;
import com.api.entity.account.BankReceipt;

@Repository
public interface BankReceiptRepository extends JpaRepository<BankReceipt, Long>{
	
	List<BankReceipt> findByschoolUdise(School school);
}

