package com.api.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import com.api.dto.AttendanceDto;
import com.api.entity.Attendance;

public interface AttendanceService {
	
	 Attendance saveAttendance(AttendanceDto attendanceDTO);
	    List<Attendance> getAllAttendances();
	    void markAbsent(Long studentId, LocalDate date);
	    void markSundaysForSchool(Long udiseNo, YearMonth monthYear);
	    void markHolidayForSchool(Long udiseNo, LocalDate date);
	    void recalculateAttendanceStatistics(Long udiseNo, YearMonth monthYear);
	
}
