package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.School;
import com.api.entity.account.ContraPayment;

@Repository
public interface ContraPaymentRepository extends JpaRepository<ContraPayment, Long> {

	public List<ContraPayment> findBySchoolUdise(School school);
	
}
