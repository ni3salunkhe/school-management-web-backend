package com.api.service;

import java.util.List;

import com.api.entity.Student;

public interface StudentService {
	
	public Student post(Student student);
	
	public List<Student> getdata();
	
	public Student getbyid(long id);
	
	public void deletedata(long id);
	
}
