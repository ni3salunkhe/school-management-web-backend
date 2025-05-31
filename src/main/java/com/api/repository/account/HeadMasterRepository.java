package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.School;
import com.api.entity.account.HeadMaster;

@Repository
public interface HeadMasterRepository extends JpaRepository<HeadMaster, Long>{
	
	List<HeadMaster> findBySchoolUdise(School udiseNo);
	
	HeadMaster findByHeadName(String headName);
}
