package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.School;
import com.api.entity.account.BankPayment;

@Repository
public interface BankPaymentRepository extends JpaRepository<BankPayment, Long>{

	List<BankPayment> findByschoolUdise(School school);
	
}
