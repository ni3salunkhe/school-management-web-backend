package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.School;
import com.api.entity.account.BankMaster;

public interface BankMasterRepository extends JpaRepository<BankMaster, Long>{
	
	List<BankMaster> findBySchoolUdiseNo(School udiseNo);
	
}
