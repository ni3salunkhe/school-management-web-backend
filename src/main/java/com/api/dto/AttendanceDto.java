package com.api.dto;

<<<<<<< HEAD
import java.time.YearMonth;

import lombok.Data;

@Data
public class AttendanceDto {
	
	 private long studentRegisterId;
	    private long udiseNo;
	    private long staffId;
	    private String teacherQualification;
	    private String division;
	    private String medium;
	    private YearMonth monthnyear;
	    private int totalDays;
	    private int workDays;
	    private int sundays;
	    private int holidays;
	    private int totala;
	    private int totalp;
	    private double totalPPercentage;
	    private int std;
	    private String stdInWords;
	    private String status;
	
=======
import lombok.Data;
import java.time.YearMonth;

@Data
public class AttendanceDto {
    private long studentRegisterId;
    private long udiseNo;
    private long staffId;
    private String teacherQualification;
    private String division;
    private String medium;
    private YearMonth monthnyear;
    private int totalDays;
    private int workDays;
    private int sundays;
    private int holidays;
    private int totala;
    private int totalp;
    private double totalPPercentage;
    private int std;
    private String stdInWords;
    private String status;
>>>>>>> 6237fb77c5d87fbfea1df0447b31e3c5c9d7d20b
}
