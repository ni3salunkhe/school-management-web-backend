package com.api.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.account.YearMaster;

@Repository
public interface YearMasterRepository extends JpaRepository<YearMaster, Long> {
	
	
	
}
