package com.api.service;

import java.util.List;
import com.api.entity.Student;

public interface StudentService {
    Student post(Student student);
    List<Student> getdata();
    Student getbyid(long id);
    void deletedata(long id);
    List<Student> getStudentsBySchool(long udiseNo);
}
