package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.dto.account.GeneralLedgerDto;
import com.api.entity.School;
import com.api.entity.account.GeneralLedger;
import com.api.entity.account.SubHeadMaster;

public interface GeneralLedgerRepository extends JpaRepository<GeneralLedger, Long> {

	List<GeneralLedger> findByShopId(School udiseNo);
	
	List<GeneralLedger> findBySubhead(SubHeadMaster subheadid);
	
}
