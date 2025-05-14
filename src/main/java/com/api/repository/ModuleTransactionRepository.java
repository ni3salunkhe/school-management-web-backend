package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.ModuleTransaction;
import com.api.entity.School;

@Repository
public interface ModuleTransactionRepository extends JpaRepository<ModuleTransaction, Long> {
	
	List<ModuleTransaction> findByschool(School school);
	
}
