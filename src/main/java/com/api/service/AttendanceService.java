//package com.api.service;
//
//import com.api.dto.AttendanceDto;
//import com.api.entity.Attendance;
//import com.api.entity.Student;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public interface AttendanceService {
//    Attendance saveAttendance(AttendanceDto attendanceDTO);
//    List<Attendance> getAllAttendances();
//    void markAbsent(Long studentId, LocalDate date);
//
//}
//package com.api.service;
//import com.api.dto.AttendanceDto;
//import com.api.entity.Attendance;
//import com.api.entity.Student;
//import java.time.LocalDate;
//import java.time.YearMonth;
//import java.util.List;
//
//public interface AttendanceService {
//    Attendance saveAttendance(AttendanceDto attendanceDTO);
//    List<Attendance> getAllAttendances();
//    void markAbsent(Long studentId, LocalDate date);
//    void markSundays(YearMonth monthYear);
//    void markHoliday(LocalDate date);
//    void recalculateAttendanceStatistics(YearMonth monthYear);
//}

package com.api.service;
import com.api.dto.AttendanceDto;
import com.api.entity.Attendance;
import com.api.entity.Student;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface AttendanceService {
    Attendance saveAttendance(AttendanceDto attendanceDTO);
    List<Attendance> getAllAttendances();
    void markAbsent(Long studentId, LocalDate date);
    void markSundaysForSchool(Long udiseNo, YearMonth monthYear);
    void markHolidayForSchool(Long udiseNo, LocalDate date);
    void recalculateAttendanceStatistics(Long udiseNo, YearMonth monthYear);
}