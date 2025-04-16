<<<<<<< HEAD
package com.api.repository;

=======

package com.api.repository;

import com.api.entity.Attendance;
import com.api.entity.School;
import com.api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> daa3f1e132236efd940915c9b3a2134fc7401fc1
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
=======
>>>>>>> daa3f1e132236efd940915c9b3a2134fc7401fc1

import com.api.entity.Attendance;
import com.api.entity.School;
import com.api.entity.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
<<<<<<< HEAD
	List<Attendance> findBySchool_UdiseNoAndMonthnyear(long udiseNo, YearMonth monthnyear);
	List<Attendance> findByRegisterNumberAndSchoolAndMonthnyear(Long registerNumber, School school, YearMonth monthYear);
	Optional<Attendance> findByStudIdAndSchoolAndMonthnyear(Student student, School school, YearMonth monthYear);
	List<Attendance> findBySchoolAndMonthnyear(School school, YearMonth monthYear);
	List<Attendance> findBySchoolAndStdAndMonthnyear(School school, int std, YearMonth monthnyear);
	List<Attendance> findBySchoolAndStdAndRegisterNumberAndMonthnyear(School school, int std, long registerNumber, YearMonth monthnyear);
=======

	Optional<Attendance> findByStudentRegisterIdAndMonthnyear(Student student, YearMonth monthnyear);

	List<Attendance> findByMonthnyear(YearMonth monthnyear);

	List<Attendance> findByUdiseNo(School school);

	List<Attendance> findByUdiseNoAndMonthnyear(School school, YearMonth monthnyear);

>>>>>>> daa3f1e132236efd940915c9b3a2134fc7401fc1
}
