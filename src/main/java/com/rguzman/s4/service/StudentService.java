package com.rguzman.s4.service;

import java.util.List;

import com.rguzman.s4.model.Student;

public interface StudentService {
	public List<Student> listStudents();
	public Student getStudent(Integer studentId);
	public Student createStudent(Student student);
	public Student modifyStudent(Student student);
	public void deleteStudent(Integer studentId);
	
}
