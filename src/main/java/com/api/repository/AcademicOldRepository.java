package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.AcademicOld;
import com.api.entity.Student;

@Repository
public interface AcademicOldRepository extends JpaRepository<AcademicOld, Long>{

	List<AcademicOld> findByStudentId(Student student);
	
}
