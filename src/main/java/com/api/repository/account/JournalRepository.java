package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.entity.account.JournalPayment;

@Repository
public interface JournalRepository extends JpaRepository<JournalPayment, Long>{
	
	List<JournalPayment> findBySchoolUdise_UdiseNo(Long udiseNo);
	
	@Query("SELECT MAX(j.transactionKey) FROM JournalPayment j")
	Long findMaxTransactionKey();
	
}
