package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{

}
