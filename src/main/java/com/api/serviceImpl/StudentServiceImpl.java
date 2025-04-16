package com.api.serviceImpl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Student;
import com.api.repository.StudentRepository;
import com.api.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student post(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getdata() {
        return studentRepository.findAll();
    }

    @Override
    public Student getbyid(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deletedata(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentsBySchool(long udiseNo) {
        return studentRepository.findBySchoolUdiseNo(udiseNo);
    }
}
