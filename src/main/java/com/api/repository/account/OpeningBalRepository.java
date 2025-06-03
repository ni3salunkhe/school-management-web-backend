package com.api.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.entity.account.OpeningBal;

@Repository
public interface OpeningBalRepository extends JpaRepository<OpeningBal, Long>{

	@Query("SELECT SUM(o.drAmt), SUM(o.crAmt) FROM OpeningBal o")
	Object[] getTotalCreditAndDebit();

	
}
