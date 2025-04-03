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
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByStudentRegisterIdAndMonthnyear(Student student, YearMonth monthnyear);
    List<Attendance> findByMonthnyear(YearMonth monthnyear);
    List<Attendance> findByUdiseNo(School school);
    List<Attendance> findByUdiseNoAndMonthnyear(School school, YearMonth monthnyear);
}