package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{

}
