<<<<<<< HEAD
package com.api.repository;

=======
//package com.api.repository;
//
//import com.api.entity.Attendance;
//import com.api.entity.Student;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.time.YearMonth;
//import java.util.Optional;
//
//public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
//    Optional<Attendance> findByStudentRegisterIdAndMonthnyear(Student student, YearMonth monthnyear);
//}
//package com.api.repository;
//import com.api.entity.Attendance;
//import com.api.entity.Student;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.time.YearMonth;
//import java.util.List;
//import java.util.Optional;
//
//public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
//    Optional<Attendance> findByStudentRegisterIdAndMonthnyear(Student student, YearMonth monthnyear);
//    List<Attendance> findByMonthnyear(YearMonth monthnyear);
//}

package com.api.repository;
import com.api.entity.Attendance;
import com.api.entity.School;
import com.api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> 6237fb77c5d87fbfea1df0447b31e3c5c9d7d20b
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
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
=======
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByStudentRegisterIdAndMonthnyear(Student student, YearMonth monthnyear);
    List<Attendance> findByMonthnyear(YearMonth monthnyear);
    List<Attendance> findByUdiseNo(School school);
    List<Attendance> findByUdiseNoAndMonthnyear(School school, YearMonth monthnyear);
}
>>>>>>> 6237fb77c5d87fbfea1df0447b31e3c5c9d7d20b
