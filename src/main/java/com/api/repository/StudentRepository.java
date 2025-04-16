package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.entity.School;
import com.api.entity.Student;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRegisterNumber(Long registerNumber);
    
    @Query("SELECT s FROM Student s WHERE s.school.udiseNo = :udiseNo")
    List<Student> findBySchoolUdiseNo(long udiseNo);
    
    @Query("SELECT s FROM Student s WHERE s.registerNumber IN :registerNumbers AND s.school.udiseNo = :udiseNo")
    List<Student> findByRegisterNumberInAndSchoolUdiseNo(List<Long> registerNumbers, Long udiseNo);
    
    Optional<Student> findByRegisterNumberAndSchool(Long registerNumber, School school);
    
    
    
}
