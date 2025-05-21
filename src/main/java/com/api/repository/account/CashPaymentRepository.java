package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.School;
import com.api.entity.account.CashPayment;
import com.api.entity.account.CashReceipt;

@Repository
public interface CashPaymentRepository extends JpaRepository<CashPayment, Long>{

	List<CashPayment> findByschoolUdise(School school);
	
}
