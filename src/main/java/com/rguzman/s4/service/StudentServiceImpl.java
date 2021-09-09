package com.rguzman.s4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rguzman.s4.model.Student;
import com.rguzman.s4.model.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	

	@Override
	public List<Student> listStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudent(Integer studentId) {
		return studentRepository.findById(studentId).orElse(null);
	}

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
		
	}

	@Override
	public Student modifyStudent(Student student) {
		return studentRepository.save(student);
		
	}

	@Override
	public  void deleteStudent(Integer studentId) {
		
		studentRepository.deleteById(studentId);
		
		
	}

}
