package com.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Student;
import com.api.repository.StudentRepository;
import com.api.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student post(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getdata() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public Student getbyid(long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).orElse(null);
	}

	@Override
	public void deletedata(long id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(id);
	}

}
