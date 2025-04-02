package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Division;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long>{
	
	boolean existsByName(String name);
	
}
