package com.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.StudentDto;
import com.api.entity.Student;

import com.api.service.DistrictService;
import com.api.service.SchoolService;
import com.api.service.StateService;
import com.api.service.StudentService;
import com.api.service.TehsilService;
import com.api.service.VillageService;

@RestController
@RequestMapping("api/student")
public class StudentController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private TehsilService tehsilService;
	
	@Autowired
	private VillageService villageService;
	
	
	@PostMapping("/")
	public ResponseEntity<Student> savedata(@RequestBody StudentDto studentDto)
	{
		Student student=new Student();
		
		student.setRegisterNumber(studentDto.getRegisterNumber());
		student.setSchool(schoolService.getbyid(studentDto.getSchool()));
		student.setApparId(studentDto.getApparId());
		student.setStudentId(studentDto.getStudentId());
		student.setAdhaarNumber(studentDto.getAdhaarNumber());
		student.setGender(studentDto.getGender());
		student.setSurName(studentDto.getSurName());
		student.setStudentName(studentDto.getStudentName());
		student.setFatherName(studentDto.getFatherName());
		student.setMotherName(studentDto.getMotherName());
		student.setNationality(studentDto.getNationality());
		student.setMotherTongue(studentDto.getMotherTongue());
		student.setReligion(studentDto.getReligion());
		student.setCaste(studentDto.getCaste());
		student.setSubCast(studentDto.getSubCast());
		student.setVillageOfBirth(villageService.getbyid(studentDto.getVillageOfBirth()));
		student.setTehasilOfBirth(tehsilService.getbyid(studentDto.getTehasilOfBirth()));
		student.setDistrictOfBirth(districtService.getbyid(studentDto.getDistrictOfBirth()));
		student.setStateOfBirth(stateService.getbyid(studentDto.getStateOfBirth()));
		student.setDateOfBirth(studentDto.getDateOfBirth());
		student.setDateOfBirthInWord(studentDto.getDateOfBirthInWord());
		student.setLastSchoolUdiseNo(studentDto.getLastSchoolUdiseNo());
		student.setAdmissionDate(studentDto.getAdmissionDate());
		student.setWhichStandardAdmitted(studentDto.getWhichStandardAdmitted());
		student.setCreatedAt(studentDto.getCreatedAt());
		student.setCurrentAcadmicYear(studentDto.getCurrentAcadmicYear());
		student.setEbcInformation(studentDto.getEbcInformation());
		student.setMinorityInformation(studentDto.getMinorityInformation());
		student.setMobileNo(studentDto.getMobileNo());
		student.setResidentialAddress(studentDto.getResidentialAddress());

		Student saveStudent=studentService.post(student);
		
		return new ResponseEntity<Student>(saveStudent,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Student>> getdata(){
		List<Student> student=studentService.getdata();
		return new ResponseEntity<List<Student>>(student,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getbyid(@PathVariable long id)
	{
		Student student=studentService.getbyid(id);
		return new ResponseEntity<Student>(student,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> editdata(@PathVariable long id,@RequestBody StudentDto studentDto)
	{
		Student student=studentService.getbyid(id);
		if(student==null)
		{
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		
		else {
			student.setRegisterNumber(studentDto.getRegisterNumber());
			student.setSchool(schoolService.getbyid(studentDto.getSchool()));
			student.setApparId(studentDto.getApparId());
			student.setStudentId(studentDto.getStudentId());
			student.setAdhaarNumber(studentDto.getAdhaarNumber());
			student.setGender(studentDto.getGender());
			student.setSurName(studentDto.getSurName());
			student.setStudentName(studentDto.getStudentName());
			student.setFatherName(studentDto.getFatherName());
			student.setMotherName(studentDto.getMotherName());
			student.setNationality(studentDto.getNationality());
			student.setMotherTongue(studentDto.getMotherTongue());
			student.setReligion(studentDto.getReligion());
			student.setCaste(studentDto.getCaste());
			student.setSubCast(studentDto.getSubCast());
			student.setVillageOfBirth(villageService.getbyid(studentDto.getVillageOfBirth()));
			student.setTehasilOfBirth(tehsilService.getbyid(studentDto.getTehasilOfBirth()));
			student.setDistrictOfBirth(districtService.getbyid(studentDto.getDistrictOfBirth()));
			student.setStateOfBirth(stateService.getbyid(studentDto.getStateOfBirth()));
			student.setDateOfBirth(studentDto.getDateOfBirth());
			student.setDateOfBirthInWord(studentDto.getDateOfBirthInWord());
			student.setLastSchoolUdiseNo(studentDto.getLastSchoolUdiseNo());
			student.setAdmissionDate(studentDto.getAdmissionDate());
			student.setWhichStandardAdmitted(studentDto.getWhichStandardAdmitted());
			student.setCreatedAt(studentDto.getCreatedAt());
			student.setCurrentAcadmicYear(studentDto.getCurrentAcadmicYear());
			student.setEbcInformation(studentDto.getEbcInformation());
			student.setMinorityInformation(studentDto.getMinorityInformation());
			student.setMobileNo(studentDto.getMobileNo());
			student.setResidentialAddress(studentDto.getResidentialAddress());

			Student saveStudent=studentService.post(student);
			
			return new ResponseEntity<Student>(saveStudent,HttpStatus.OK);

		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletedata(@PathVariable long id)
	{
		studentService.deletedata(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/school/{udiseNo}")
	public ResponseEntity<?> getStudentsBySchool(@PathVariable long udiseNo) {
		List<Student> students = studentService.getStudentsBySchool(udiseNo);
		if (students.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(students);
	}
}
