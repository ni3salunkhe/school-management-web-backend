package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.School;
import com.api.entity.account.GeneralLedger;

public interface GeneralLedgerRepository extends JpaRepository<GeneralLedger, Long> {

	List<GeneralLedger> findByShopId(School udiseNo);
	
}
