package com.api.dto;

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
}
