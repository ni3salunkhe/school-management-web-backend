package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.entity.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

	Developer findByUsername(String username);
	
	Developer findByActiveTrue();

}