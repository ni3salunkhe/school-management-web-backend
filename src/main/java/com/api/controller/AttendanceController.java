package com.api.controller;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.dto.AttendanceDto;
import com.api.entity.Attendance;
import com.api.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    
    @PostMapping("/mark-absent/{studentId}")
    public ResponseEntity<String> markAbsent(
            @PathVariable Long studentId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        attendanceService.markAbsent(studentId, date);
        return ResponseEntity.ok("Attendance marked as Absent for student ID: " + studentId + " on " + date);
    }
    
    @PostMapping("/mark-holiday/school/{udiseNo}")
    public ResponseEntity<String> markHolidayForSchool(
            @PathVariable Long udiseNo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        attendanceService.markHolidayForSchool(udiseNo, date);
        return ResponseEntity.ok("Day marked as Holiday for school UDISE: " + udiseNo + " on " + date);
    }
    
    @PostMapping("/mark-sundays/school/{udiseNo}")
    public ResponseEntity<String> markSundaysForSchool(
            @PathVariable Long udiseNo,
            @RequestParam int year,
            @RequestParam int month) {
        
        YearMonth yearMonth = YearMonth.of(year, month);
        attendanceService.markSundaysForSchool(udiseNo, yearMonth);
        return ResponseEntity.ok("Sundays marked for school UDISE: " + udiseNo + " for month: " + yearMonth);
    }
    
    @PostMapping("/recalculate/school/{udiseNo}")
    public ResponseEntity<String> recalculateStatisticsForSchool(
            @PathVariable Long udiseNo,
            @RequestParam int year,
            @RequestParam int month) {
        
        YearMonth yearMonth = YearMonth.of(year, month);
        attendanceService.recalculateAttendanceStatistics(udiseNo, yearMonth);
        return ResponseEntity.ok("Attendance statistics recalculated for school UDISE: " + udiseNo + " for month: " + yearMonth);
    }
    
    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@RequestBody AttendanceDto attendanceDTO) {
        return ResponseEntity.ok(attendanceService.saveAttendance(attendanceDTO));
    }
    
    @GetMapping
    public ResponseEntity<List<Attendance>> getAllAttendances() {
        return ResponseEntity.ok(attendanceService.getAllAttendances());
    }
}
//package com.api.controller;
//
//import java.time.LocalDate;
//import java.time.YearMonth;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import com.api.dto.AttendanceDto;
//import com.api.entity.Attendance;
//import com.api.service.AttendanceService;
//
//@RestController
//@RequestMapping("/api/attendance")
//public class AttendanceController {
//    @Autowired
//    private AttendanceService attendanceService;
//    
//    @PostMapping("/mark-absent/{studentId}")
//    public ResponseEntity<String> markAbsent(
//            @PathVariable Long studentId,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        
//        attendanceService.markAbsent(studentId, date);
//        return ResponseEntity.ok("Attendance marked as Absent for student ID: " + studentId + " on " + date);
//    }
//    
//    @PostMapping("/mark-holiday")
//    public ResponseEntity<String> markHoliday(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        
//        attendanceService.markHoliday(date);
//        return ResponseEntity.ok("Day marked as Holiday for all students on " + date);
//    }
//    
//    @PostMapping("/mark-sundays/{year}/{month}")
//    public ResponseEntity<String> markSundays(
//            @PathVariable int year,
//            @PathVariable int month) {
//        
//        YearMonth yearMonth = YearMonth.of(year, month);
//        attendanceService.markSundays(yearMonth);
//        return ResponseEntity.ok("Sundays marked for " + yearMonth);
//    }
//    
//    @PostMapping("/recalculate/{year}/{month}")
//    public ResponseEntity<String> recalculateStatistics(
//            @PathVariable int year,
//            @PathVariable int month) {
//        YearMonth yearMonth = YearMonth.of(year, month);
//        attendanceService.recalculateAttendanceStatistics(yearMonth);
//        return ResponseEntity.ok("Attendance statistics recalculated for " + yearMonth);
//    }
//    
//    @PostMapping
//    public ResponseEntity<Attendance> createAttendance(@RequestBody AttendanceDto attendanceDTO) {
//        return ResponseEntity.ok(attendanceService.saveAttendance(attendanceDTO));
//    }
//    
//    @GetMapping
//    public ResponseEntity<List<Attendance>> getAllAttendances() {
//        return ResponseEntity.ok(attendanceService.getAllAttendances());
//    }
//}

//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.api.dto.AttendanceDto;
//import com.api.entity.Attendance;
//import com.api.service.AttendanceService;
//
//@RestController
//@RequestMapping("/api/attendance")
//public class AttendanceController {
//
//
//    @Autowired
//    private AttendanceService attendanceService;
//    
//
//    @PostMapping("/mark-absent/{studentId}")
//    public ResponseEntity<String> markAbsent(
//            @PathVariable Long studentId,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        
//        attendanceService.markAbsent(studentId, date);
//        return ResponseEntity.ok("Attendance marked as Absent for student ID: " + studentId + " on " + date);
//    }
//    
//    @PostMapping
//    public ResponseEntity<Attendance> createAttendance(@RequestBody AttendanceDto attendanceDTO) {
//        return ResponseEntity.ok(attendanceService.saveAttendance(attendanceDTO));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Attendance>> getAllAttendances() {
//        return ResponseEntity.ok(attendanceService.getAllAttendances());
//    }
//    
//}
