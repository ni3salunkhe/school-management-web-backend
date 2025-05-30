package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long>{
	School findByHeadMasterUserName(String username);
	
	School findByHeadMasterEmailIdAndHeadMasterMobileNo(String email,String mobile);
}
