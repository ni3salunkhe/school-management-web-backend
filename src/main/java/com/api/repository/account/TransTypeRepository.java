package com.api.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.School;
import com.api.entity.account.TransType;

public interface TransTypeRepository extends JpaRepository<TransType, Long>{
	List<TransType> findBySchoolUdiseNo(School udiseNo);
}
