package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Holiday;
import com.api.entity.School;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long>{
	List<Holiday> findByUdise(School udiseNo);
}
