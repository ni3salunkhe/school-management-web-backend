package com.api.repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Attendance;
import com.api.entity.School;
import com.api.entity.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	Optional<Attendance> findByStudentRegisterIdAndMonthnyear(Student student, YearMonth monthnyear);

	List<Attendance> findByMonthnyear(YearMonth monthnyear);

	List<Attendance> findByUdiseNo(School school);

	List<Attendance> findByUdiseNoAndMonthnyear(School school, YearMonth monthnyear);

}
