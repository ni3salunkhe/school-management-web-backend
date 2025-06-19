package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.account.ExpensesVouchar;

@Repository
public interface ExpensesVoucharRepository extends JpaRepository<ExpensesVouchar, Long>{
	
	List<ExpensesVouchar> findBySchoolUdiseUdiseNo(long udiseNo);
	
}
