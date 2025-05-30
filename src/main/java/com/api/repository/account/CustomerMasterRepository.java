package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.School;
import com.api.entity.account.CustomerMaster;

@Repository
public interface CustomerMasterRepository extends JpaRepository<CustomerMaster, Long>{

	List<CustomerMaster> findBySchoolUdise(School school);
	
}
