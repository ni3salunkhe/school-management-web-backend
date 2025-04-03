package com.api.serviceImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import com.api.dto.AttendanceDto;
import com.api.entity.Attendance;
import com.api.entity.School;
import com.api.entity.Staff;
import com.api.entity.Student;
import com.api.repository.AttendanceRepository;
import com.api.repository.SchoolRepository;
import com.api.repository.StaffRepository;
import com.api.repository.StudentRepository;
import com.api.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public Attendance saveAttendance(AttendanceDto attendanceDTO) {
        Attendance attendance = new Attendance();
        
        Student student = studentRepository.findById(attendanceDTO.getStudentRegisterId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        School school = schoolRepository.findById(attendanceDTO.getUdiseNo())
                .orElseThrow(() -> new RuntimeException("School not found"));
        Staff staff = staffRepository.findById(attendanceDTO.getStaffId())
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        // Only set these values from the DTO
        attendance.setStudentRegisterId(student);
        attendance.setUdiseNo(school);
        attendance.setStaffId(staff);
        attendance.setTeacherQualification(attendanceDTO.getTeacherQualification());
        attendance.setDivision(attendanceDTO.getDivision());
        attendance.setMedium(attendanceDTO.getMedium());
        attendance.setStd(attendanceDTO.getStd());
        attendance.setStdInWords(attendanceDTO.getStdInWords());
        
        // Get current month and year if not provided
        YearMonth monthYear = (attendanceDTO.getMonthnyear() != null) ? 
                attendanceDTO.getMonthnyear() : YearMonth.now();
        attendance.setMonthnyear(monthYear);
        
        // Dynamically calculate days in month
        int totalDays = monthYear.lengthOfMonth();
        attendance.setTotalDays(totalDays);
        
        // Calculate Sundays in the month
        int sundaysCount = countSundays(monthYear);
        attendance.setSundays(sundaysCount);
        
        // Initialize holidays to 0, will be updated when holidays are marked
        attendance.setHolidays(0);
        
        // Calculate workdays: totalDays - sundays (holidays will be calculated later)
        int workDays = totalDays - sundaysCount;
        attendance.setWorkDays(workDays);
        
        // Initialize totals - initially all workdays are present
        attendance.setTotala(0);
        attendance.setTotalp(workDays);
        
        // Calculate initial percentage
        double percentage = 100.0; // Initially 100% present
        attendance.setTotalPPercentage(percentage);
        
        // Set status as active
        attendance.setStatus("Active");
        
        // Initialize all days as Present 'P'
        initializeDaysAsPresent(attendance);
        
        // Mark Sundays as 'S'
        markSundaysForAttendance(attendance, monthYear);

        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }
    
    @Override
    public void markAbsent(Long studentId, LocalDate date) {
        YearMonth monthYear = YearMonth.from(date);
        int dayOfMonth = date.getDayOfMonth();

        // Fetch Student first
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Fetch Attendance using correct method
        Optional<Attendance> optionalAttendance = attendanceRepository.findByStudentRegisterIdAndMonthnyear(student, monthYear);

        Attendance attendance = optionalAttendance.orElseThrow(() -> new RuntimeException("Attendance record not found"));

        String currentStatus = getDayStatus(attendance, dayOfMonth);
        
        // Only mark absent if the day is currently marked as Present (P)
        if ("P".equalsIgnoreCase(currentStatus)) {
            // Set the day as "A" (Absent)
            setDayStatus(attendance, dayOfMonth, "A");
    
            // Update absent count and percentage
            attendance.setTotala(attendance.getTotala() + 1);
            attendance.setTotalp(attendance.getTotalp() - 1);
            
            // Recalculate the percentage based on workdays
            double percentage = (attendance.getWorkDays() > 0) 
                ? ((double) attendance.getTotalp() / attendance.getWorkDays()) * 100 
                : 0;
            attendance.setTotalPPercentage(percentage);
    
            // Save the updated record
            attendanceRepository.save(attendance);
        }
    }
    
    @Override
    public void markSundaysForSchool(Long udiseNo, YearMonth monthYear) {
        // Find the school
        School school = schoolRepository.findById(udiseNo)
                .orElseThrow(() -> new RuntimeException("School not found"));
                
        // Find all attendance records for the given month and school
        List<Attendance> attendances = attendanceRepository.findByUdiseNoAndMonthnyear(school, monthYear);
        
        for (Attendance attendance : attendances) {
            markSundaysForAttendance(attendance, monthYear);
            attendanceRepository.save(attendance);
        }
    }
    
    private void markSundaysForAttendance(Attendance attendance, YearMonth monthYear) {
        int year = monthYear.getYear();
        int month = monthYear.getMonthValue();
        int lengthOfMonth = monthYear.lengthOfMonth();
        
        for (int day = 1; day <= lengthOfMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                setDayStatus(attendance, day, "S");
            }
        }
    }
    
    @Override
    public void markHolidayForSchool(Long udiseNo, LocalDate date) {
        YearMonth monthYear = YearMonth.from(date);
        int dayOfMonth = date.getDayOfMonth();
        
        // Find the school
        School school = schoolRepository.findById(udiseNo)
                .orElseThrow(() -> new RuntimeException("School not found"));
                
        // Find all attendance records for the given month and school
        List<Attendance> attendances = attendanceRepository.findByUdiseNoAndMonthnyear(school, monthYear);
        
        if (attendances.isEmpty()) {
            throw new RuntimeException("No attendance records found for school with UDISE: " + udiseNo + " for " + monthYear);
        }
        
        boolean isNewHoliday = false;
        
        for (Attendance attendance : attendances) {
            // Do not mark holidays on Sundays
            String currentStatus = getDayStatus(attendance, dayOfMonth);
            if (!"S".equalsIgnoreCase(currentStatus) && !"H".equalsIgnoreCase(currentStatus)) {
                isNewHoliday = true;
                
                // Only update counts if changing from Present to Holiday
                if ("P".equalsIgnoreCase(currentStatus)) {
                    attendance.setTotalp(attendance.getTotalp() - 1);
                } else if ("A".equalsIgnoreCase(currentStatus)) {
                    attendance.setTotala(attendance.getTotala() - 1);
                }
                
                setDayStatus(attendance, dayOfMonth, "H");
                
                // Update workdays (reduce by 1 since this day is now a holiday)
                attendance.setWorkDays(attendance.getWorkDays() - 1);
                
                // Recalculate the percentage
                double percentage = (attendance.getWorkDays() > 0) 
                    ? ((double) attendance.getTotalp() / attendance.getWorkDays()) * 100 
                    : 0;
                attendance.setTotalPPercentage(percentage);
                
                attendanceRepository.save(attendance);
            }
        }
        
        // If this is a new holiday, increment the holiday count for all attendances
        if (isNewHoliday) {
            for (Attendance attendance : attendances) {
                attendance.setHolidays(attendance.getHolidays() + 1);
                attendanceRepository.save(attendance);
            }
        }
    }
    
    @Override
    public void recalculateAttendanceStatistics(Long udiseNo, YearMonth monthYear) {
        // Find the school
        School school = schoolRepository.findById(udiseNo)
                .orElseThrow(() -> new RuntimeException("School not found"));
                
        // Find all attendance records for the given month and school
        List<Attendance> attendances = attendanceRepository.findByUdiseNoAndMonthnyear(school, monthYear);
        
        for (Attendance attendance : attendances) {
            // Count present, absent, holidays, and Sundays
            int presentCount = 0;
            int absentCount = 0;
            int holidayCount = 0;
            int sundayCount = 0;
            
            int daysInMonth = monthYear.lengthOfMonth();
            
            for (int day = 1; day <= daysInMonth; day++) {
                String status = getDayStatus(attendance, day);
                switch (status.toUpperCase()) {
                    case "P" -> presentCount++;
                    case "A" -> absentCount++;
                    case "H" -> holidayCount++;
                    case "S" -> sundayCount++;
                }
            }
            
            // Update the attendance record
            attendance.setTotalp(presentCount);
            attendance.setTotala(absentCount);
            attendance.setHolidays(holidayCount);
            attendance.setSundays(sundayCount);
            
            // Work days are days student should be present (excluding Sundays and holidays)
            int workDays = daysInMonth - (sundayCount + holidayCount);
            attendance.setWorkDays(workDays);
            
            // Calculate percentage
            double percentage = (workDays > 0) 
                ? ((double) presentCount / workDays) * 100 
                : 0;
            attendance.setTotalPPercentage(percentage);
            
            attendanceRepository.save(attendance);
        }
    }
    
    // Method to count Sundays in a month
    private int countSundays(YearMonth monthYear) {
        int year = monthYear.getYear();
        int month = monthYear.getMonthValue();
        int lengthOfMonth = monthYear.lengthOfMonth();
        
        int sundayCount = 0;
        for (int day = 1; day <= lengthOfMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                sundayCount++;
            }
        }
        
        return sundayCount;
    }
    
    // Initialize all days as Present
    private void initializeDaysAsPresent(Attendance attendance) {
        for (int day = 1; day <= 31; day++) {
            setDayStatus(attendance, day, "P");
        }
    }
    
    // Helper method to get the status for a specific day
    private String getDayStatus(Attendance attendance, int day) {
        return switch (day) {
            case 1 -> attendance.getDay1();
            case 2 -> attendance.getDay2();
            case 3 -> attendance.getDay3();
            case 4 -> attendance.getDay4();
            case 5 -> attendance.getDay5();
            case 6 -> attendance.getDay6();
            case 7 -> attendance.getDay7();
            case 8 -> attendance.getDay8();
            case 9 -> attendance.getDay9();
            case 10 -> attendance.getDay10();
            case 11 -> attendance.getDay11();
            case 12 -> attendance.getDay12();
            case 13 -> attendance.getDay13();
            case 14 -> attendance.getDay14();
            case 15 -> attendance.getDay15();
            case 16 -> attendance.getDay16();
            case 17 -> attendance.getDay17();
            case 18 -> attendance.getDay18();
            case 19 -> attendance.getDay19();
            case 20 -> attendance.getDay20();
            case 21 -> attendance.getDay21();
            case 22 -> attendance.getDay22();
            case 23 -> attendance.getDay23();
            case 24 -> attendance.getDay24();
            case 25 -> attendance.getDay25();
            case 26 -> attendance.getDay26();
            case 27 -> attendance.getDay27();
            case 28 -> attendance.getDay28();
            case 29 -> attendance.getDay29();
            case 30 -> attendance.getDay30();
            case 31 -> attendance.getDay31();
            default -> throw new IllegalArgumentException("Invalid day: " + day);
        };
    }
    
    private void setDayStatus(Attendance attendance, int day, String status) {
        switch (day) {
            case 1 -> attendance.setDay1(status);
            case 2 -> attendance.setDay2(status);
            case 3 -> attendance.setDay3(status);
            case 4 -> attendance.setDay4(status);
            case 5 -> attendance.setDay5(status);
            case 6 -> attendance.setDay6(status);
            case 7 -> attendance.setDay7(status);
            case 8 -> attendance.setDay8(status);
            case 9 -> attendance.setDay9(status);
            case 10 -> attendance.setDay10(status);
            case 11 -> attendance.setDay11(status);
            case 12 -> attendance.setDay12(status);
            case 13 -> attendance.setDay13(status);
            case 14 -> attendance.setDay14(status);
            case 15 -> attendance.setDay15(status);
            case 16 -> attendance.setDay16(status);
            case 17 -> attendance.setDay17(status);
            case 18 -> attendance.setDay18(status);
            case 19 -> attendance.setDay19(status);
            case 20 -> attendance.setDay20(status);
            case 21 -> attendance.setDay21(status);
            case 22 -> attendance.setDay22(status);
            case 23 -> attendance.setDay23(status);
            case 24 -> attendance.setDay24(status);
            case 25 -> attendance.setDay25(status);
            case 26 -> attendance.setDay26(status);
            case 27 -> attendance.setDay27(status);
            case 28 -> attendance.setDay28(status);
            case 29 -> attendance.setDay29(status);
            case 30 -> attendance.setDay30(status);
            case 31 -> attendance.setDay31(status);
            default -> throw new IllegalArgumentException("Invalid day: " + day);
        }
    }
}

//public class AttendanceServiceImpl implements AttendanceService {
//
//    @Autowired
//    private AttendanceRepository attendanceRepository;
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Autowired
//    private SchoolRepository schoolRepository;
//
//    @Autowired
//    private StaffRepository staffRepository;
//
//    @Override
//    public Attendance saveAttendance(AttendanceDto attendanceDTO) {
//        Attendance attendance = new Attendance();
//        
//        Student student = studentRepository.findById(attendanceDTO.getStudentRegisterId())
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//        School school = schoolRepository.findById(attendanceDTO.getUdiseNo())
//                .orElseThrow(() -> new RuntimeException("School not found"));
//        Staff staff = staffRepository.findById(attendanceDTO.getStaffId())
//                .orElseThrow(() -> new RuntimeException("Staff not found"));
//
//        // Set only the basic data from DTO as requested
//        attendance.setStudentRegisterId(student);
//        attendance.setUdiseNo(school);
//        attendance.setStaffId(staff);
//        attendance.setTeacherQualification(attendanceDTO.getTeacherQualification());
//        attendance.setDivision(attendanceDTO.getDivision());
//        attendance.setMedium(attendanceDTO.getMedium());
//        attendance.setStd(attendanceDTO.getStd());
//        attendance.setStdInWords(attendanceDTO.getStdInWords());
//        attendance.setStatus("Active"); // Default status
//        
//        // Set the month and year
//        YearMonth monthYear = attendanceDTO.getMonthnyear();
//        attendance.setMonthnyear(monthYear);
//        
//        // Dynamically calculate days in month
//        int totalDays = monthYear.lengthOfMonth();
//        attendance.setTotalDays(totalDays);
//        
//        // Count Sundays in the month
//        int sundayCount = countSundaysInMonth(monthYear);
//        attendance.setSundays(sundayCount);
//        
//        // Initially set holidays to 0, will be updated when holidays are marked
//        attendance.setHolidays(0);
//        
//        // Calculate work days: totalDays - sundays - holidays
//        int workDays = totalDays - sundayCount;
//        attendance.setWorkDays(workDays);
//        
//        // Initialize attendance counters
//        attendance.setTotala(0);
//        attendance.setTotalp(workDays); // Initially all work days are present
//        
//        // Calculate percentage (initially 100% as all working days are present)
//        attendance.setTotalPPercentage(100.0);
//        
//        // Mark all days as Present by default
//        initializeAllDaysAsPresent(attendance);
//        
//        // Mark Sundays
//        markSundaysForAttendance(attendance, monthYear);
//
//        return attendanceRepository.save(attendance);
//    }
//
//    @Override
//    public List<Attendance> getAllAttendances() {
//        return attendanceRepository.findAll();
//    }
//    
//    @Override
//    public void markAbsent(Long studentId, LocalDate date) {
//        YearMonth monthYear = YearMonth.from(date);
//        int dayOfMonth = date.getDayOfMonth();
//
//        // Fetch Student first
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//
//        // Fetch Attendance using correct method
//        Optional<Attendance> optionalAttendance = attendanceRepository.findByStudentRegisterIdAndMonthnyear(student, monthYear);
//
//        Attendance attendance = optionalAttendance.orElseThrow(() -> new RuntimeException("Attendance record not found"));
//
//        String currentStatus = getDayStatus(attendance, dayOfMonth);
//        
//        // Only mark absent if the day is currently marked as Present (P)
//        if ("P".equalsIgnoreCase(currentStatus)) {
//            // Set the day as "A" (Absent)
//            setDayStatus(attendance, dayOfMonth, "A");
//    
//            // Update absent count and percentage
//            attendance.setTotala(attendance.getTotala() + 1);
//            attendance.setTotalp(attendance.getTotalp() - 1);
//            
//            // Recalculate the percentage based on workdays
//            updateAttendancePercentage(attendance);
//    
//            // Save the updated record
//            attendanceRepository.save(attendance);
//        }
//    }
//    
//    @Override
//    public void markSundays(YearMonth monthYear) {
//        // Find all attendance records for the given month and year
//        List<Attendance> attendances = attendanceRepository.findByMonthnyear(monthYear);
//        
//        for (Attendance attendance : attendances) {
//            markSundaysForAttendance(attendance, monthYear);
//            attendanceRepository.save(attendance);
//        }
//    }
//    
//    private void markSundaysForAttendance(Attendance attendance, YearMonth monthYear) {
//        int year = monthYear.getYear();
//        int month = monthYear.getMonthValue();
//        int lengthOfMonth = monthYear.lengthOfMonth();
//        
//        for (int day = 1; day <= lengthOfMonth; day++) {
//            LocalDate date = LocalDate.of(year, month, day);
//            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
//                String currentStatus = getDayStatus(attendance, day);
//                // Only update if not already marked as Sunday
//                if (!"S".equalsIgnoreCase(currentStatus)) {
//                    // If it was marked as present, decrement the present count
//                    if ("P".equalsIgnoreCase(currentStatus)) {
//                        attendance.setTotalp(attendance.getTotalp() - 1);
//                    } else if ("A".equalsIgnoreCase(currentStatus)) {
//                        attendance.setTotala(attendance.getTotala() - 1);
//                    }
//                    
//                    setDayStatus(attendance, day, "S");
//                }
//            }
//        }
//        
//        // Update percentage after marking all Sundays
//        updateAttendancePercentage(attendance);
//    }
//    
//    @Override
//    public void markHoliday(LocalDate date) {
//        YearMonth monthYear = YearMonth.from(date);
//        int dayOfMonth = date.getDayOfMonth();
//        
//        // Find all attendance records for the given month and year
//        List<Attendance> attendances = attendanceRepository.findByMonthnyear(monthYear);
//        
//        if (attendances.isEmpty()) {
//            throw new RuntimeException("No attendance records found for " + monthYear);
//        }
//        
//        for (Attendance attendance : attendances) {
//            // Do not mark holidays on Sundays
//            String currentStatus = getDayStatus(attendance, dayOfMonth);
//            if (!"S".equalsIgnoreCase(currentStatus) && !"H".equalsIgnoreCase(currentStatus)) {
//                // Update counters based on current status
//                if ("P".equalsIgnoreCase(currentStatus)) {
//                    attendance.setTotalp(attendance.getTotalp() - 1);
//                } else if ("A".equalsIgnoreCase(currentStatus)) {
//                    attendance.setTotala(attendance.getTotala() - 1);
//                }
//                
//                // Mark as holiday
//                setDayStatus(attendance, dayOfMonth, "H");
//                
//                // Increment holiday count
//                attendance.setHolidays(attendance.getHolidays() + 1);
//                
//                // Update work days: totalDays - sundays - holidays
//                attendance.setWorkDays(attendance.getTotalDays() - attendance.getSundays() - attendance.getHolidays());
//                
//                // Update percentage
//                updateAttendancePercentage(attendance);
//                
//                attendanceRepository.save(attendance);
//            }
//        }
//    }
//    
//    @Override
//    public void recalculateAttendanceStatistics(YearMonth monthYear) {
//        List<Attendance> attendances = attendanceRepository.findByMonthnyear(monthYear);
//        
//        for (Attendance attendance : attendances) {
//            // Count days with different statuses
//            int presentCount = 0;
//            int absentCount = 0;
//            int holidayCount = 0;
//            int sundayCount = 0;
//            
//            int daysInMonth = monthYear.lengthOfMonth();
//            
//            for (int day = 1; day <= daysInMonth; day++) {
//                String status = getDayStatus(attendance, day);
//                switch (status.toUpperCase()) {
//                    case "P" -> presentCount++;
//                    case "A" -> absentCount++;
//                    case "H" -> holidayCount++;
//                    case "S" -> sundayCount++;
//                }
//            }
//            
//            // Update the attendance record
//            attendance.setTotalp(presentCount);
//            attendance.setTotala(absentCount);
//            attendance.setHolidays(holidayCount);
//            attendance.setSundays(sundayCount);
//            
//            // Calculate total days (should match month length)
//            attendance.setTotalDays(daysInMonth);
//            
//            // Work days are total days excluding Sundays and holidays
//            attendance.setWorkDays(daysInMonth - sundayCount - holidayCount);
//            
//            // Update percentage
//            updateAttendancePercentage(attendance);
//            
//            attendanceRepository.save(attendance);
//        }
//    }
//    
//    // Helper method to count Sundays in a month
//    private int countSundaysInMonth(YearMonth monthYear) {
//        int year = monthYear.getYear();
//        int month = monthYear.getMonthValue();
//        int daysInMonth = monthYear.lengthOfMonth();
//        
//        int sundayCount = 0;
//        for (int day = 1; day <= daysInMonth; day++) {
//            LocalDate date = LocalDate.of(year, month, day);
//            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
//                sundayCount++;
//            }
//        }
//        
//        return sundayCount;
//    }
//    
//    // Helper method to update attendance percentage
//    private void updateAttendancePercentage(Attendance attendance) {
//        int workDays = attendance.getWorkDays();
//        int presentDays = attendance.getTotalp();
//        
//        double percentage = (workDays > 0) ? ((double) presentDays / workDays) * 100 : 0;
//        attendance.setTotalPPercentage(percentage);
//    }
//    
//    // Initialize all days as Present
//    private void initializeAllDaysAsPresent(Attendance attendance) {
//        for (int day = 1; day <= 31; day++) {
//            setDayStatus(attendance, day, "P");
//        }
//    }
//    
//    // Helper method to get the status for a specific day
//    private String getDayStatus(Attendance attendance, int day) {
//        return switch (day) {
//            case 1 -> attendance.getDay1();
//            case 2 -> attendance.getDay2();
//            case 3 -> attendance.getDay3();
//            case 4 -> attendance.getDay4();
//            case 5 -> attendance.getDay5();
//            case 6 -> attendance.getDay6();
//            case 7 -> attendance.getDay7();
//            case 8 -> attendance.getDay8();
//            case 9 -> attendance.getDay9();
//            case 10 -> attendance.getDay10();
//            case 11 -> attendance.getDay11();
//            case 12 -> attendance.getDay12();
//            case 13 -> attendance.getDay13();
//            case 14 -> attendance.getDay14();
//            case 15 -> attendance.getDay15();
//            case 16 -> attendance.getDay16();
//            case 17 -> attendance.getDay17();
//            case 18 -> attendance.getDay18();
//            case 19 -> attendance.getDay19();
//            case 20 -> attendance.getDay20();
//            case 21 -> attendance.getDay21();
//            case 22 -> attendance.getDay22();
//            case 23 -> attendance.getDay23();
//            case 24 -> attendance.getDay24();
//            case 25 -> attendance.getDay25();
//            case 26 -> attendance.getDay26();
//            case 27 -> attendance.getDay27();
//            case 28 -> attendance.getDay28();
//            case 29 -> attendance.getDay29();
//            case 30 -> attendance.getDay30();
//            case 31 -> attendance.getDay31();
//            default -> throw new IllegalArgumentException("Invalid day: " + day);
//        };
//    }
//    
//    private void setDayStatus(Attendance attendance, int day, String status) {
//        switch (day) {
//            case 1 -> attendance.setDay1(status);
//            case 2 -> attendance.setDay2(status);
//            case 3 -> attendance.setDay3(status);
//            case 4 -> attendance.setDay4(status);
//            case 5 -> attendance.setDay5(status);
//            case 6 -> attendance.setDay6(status);
//            case 7 -> attendance.setDay7(status);
//            case 8 -> attendance.setDay8(status);
//            case 9 -> attendance.setDay9(status);
//            case 10 -> attendance.setDay10(status);
//            case 11 -> attendance.setDay11(status);
//            case 12 -> attendance.setDay12(status);
//            case 13 -> attendance.setDay13(status);
//            case 14 -> attendance.setDay14(status);
//            case 15 -> attendance.setDay15(status);
//            case 16 -> attendance.setDay16(status);
//            case 17 -> attendance.setDay17(status);
//            case 18 -> attendance.setDay18(status);
//            case 19 -> attendance.setDay19(status);
//            case 20 -> attendance.setDay20(status);
//            case 21 -> attendance.setDay21(status);
//            case 22 -> attendance.setDay22(status);
//            case 23 -> attendance.setDay23(status);
//            case 24 -> attendance.setDay24(status);
//            case 25 -> attendance.setDay25(status);
//            case 26 -> attendance.setDay26(status);
//            case 27 -> attendance.setDay27(status);
//            case 28 -> attendance.setDay28(status);
//            case 29 -> attendance.setDay29(status);
//            case 30 -> attendance.setDay30(status);
//            case 31 -> attendance.setDay31(status);
//            default -> throw new IllegalArgumentException("Invalid day: " + day);
//        }
//    }
//}

//public class AttendanceServiceImpl implements AttendanceService {
//
//    @Autowired
//    private AttendanceRepository attendanceRepository;
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Autowired
//    private SchoolRepository schoolRepository;
//
//    @Autowired
//    private StaffRepository staffRepository;
//
//    @Override
//    public Attendance saveAttendance(AttendanceDto attendanceDTO) {
//        Attendance attendance = new Attendance();
//        
//        Student student = studentRepository.findById(attendanceDTO.getStudentRegisterId())
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//        School school = schoolRepository.findById(attendanceDTO.getUdiseNo())
//                .orElseThrow(() -> new RuntimeException("School not found"));
//        Staff staff = staffRepository.findById(attendanceDTO.getStaffId())
//                .orElseThrow(() -> new RuntimeException("Staff not found"));
//
//        attendance.setStudentRegisterId(student);
//        attendance.setUdiseNo(school);
//        attendance.setStaffId(staff);
//        attendance.setTeacherQualification(attendanceDTO.getTeacherQualification());
//        attendance.setDivision(attendanceDTO.getDivision());
//        attendance.setMedium(attendanceDTO.getMedium());
//        attendance.setMonthnyear(attendanceDTO.getMonthnyear());
//        attendance.setTotalDays(attendanceDTO.getTotalDays());
//        attendance.setWorkDays(attendanceDTO.getWorkDays());
//        attendance.setSundays(attendanceDTO.getSundays());
//        attendance.setHolidays(attendanceDTO.getHolidays());
//        attendance.setTotala(attendanceDTO.getTotala());
//        attendance.setTotalp(attendanceDTO.getTotalp());
//        attendance.setTotalPPercentage(attendanceDTO.getTotalPPercentage());
//        attendance.setStd(attendanceDTO.getStd());
//        attendance.setStdInWords(attendanceDTO.getStdInWords());
//        attendance.setStatus(attendanceDTO.getStatus());
//
//        return attendanceRepository.save(attendance);
//    }
//
//    @Override
//    public List<Attendance> getAllAttendances() {
//        return attendanceRepository.findAll();
//    }
//    
//    @Override
//    public void markAbsent(Long studentId, LocalDate date) {
//        YearMonth monthYear = YearMonth.from(date);
//        int dayOfMonth = date.getDayOfMonth();
//
//        // ✅ Fetch Student first
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//
//        // ✅ Fetch Attendance using correct method
//        Optional<Attendance> optionalAttendance = attendanceRepository.findByStudentRegisterIdAndMonthnyear(student, monthYear);
//
//        Attendance attendance = optionalAttendance.orElseThrow(() -> new RuntimeException("Attendance record not found"));
//
//        // ✅ Set the day as "A" (Absent)
//        setAbsent(attendance, dayOfMonth);
//
//        // ✅ Update absent count and percentage
//        attendance.setTotala(attendance.getTotala() + 1);
//        attendance.setTotalp(attendance.getTotalp() - 1);
//        attendance.setTotalPPercentage(((double) attendance.getTotalp() / attendance.getWorkDays()) * 100);
//
//        // ✅ Save the updated record
//        attendanceRepository.save(attendance);
//    }
//
//    private void setAbsent(Attendance attendance, int day) {
//        switch (day) {
//            case 1 -> attendance.setDay1("A");
//            case 2 -> attendance.setDay2("A");
//            case 3 -> attendance.setDay3("A");
//            case 4 -> attendance.setDay4("A");
//            case 5 -> attendance.setDay5("A");
//            case 6 -> attendance.setDay6("A");
//            case 7 -> attendance.setDay7("A");
//            case 8 -> attendance.setDay8("A");
//            case 9 -> attendance.setDay9("A");
//            case 10 -> attendance.setDay10("A");
//            case 11 -> attendance.setDay11("A");
//            case 12 -> attendance.setDay12("A");
//            case 13 -> attendance.setDay13("A");
//            case 14 -> attendance.setDay14("A");
//            case 15 -> attendance.setDay15("A");
//            case 16 -> attendance.setDay16("A");
//            case 17 -> attendance.setDay17("A");
//            case 18 -> attendance.setDay18("A");
//            case 19 -> attendance.setDay19("A");
//            case 20 -> attendance.setDay20("A");
//            case 21 -> attendance.setDay21("A");
//            case 22 -> attendance.setDay22("A");
//            case 23 -> attendance.setDay23("A");
//            case 24 -> attendance.setDay24("A");
//            case 25 -> attendance.setDay25("A");
//            case 26 -> attendance.setDay26("A");
//            case 27 -> attendance.setDay27("A");
//            case 28 -> attendance.setDay28("A");
//            case 29 -> attendance.setDay29("A");
//            case 30 -> attendance.setDay30("A");
//            case 31 -> attendance.setDay31("A");
//            default -> throw new IllegalArgumentException("Invalid day: " + day);
//        }
//    }
//    
//}
