package com.rguzman.s4.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.rguzman.s4.model.Student;
import com.rguzman.s4.model.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceMockTest {
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@MockBean
	private StudentRepository studentRepository;
	
	
	@Test
	public void testListStudents() {
		when(studentRepository.findAll()).thenReturn(Stream.of(new Student(), new Student()).collect(Collectors.toList()) );
		assertEquals(2, studentServiceImpl.listStudents().size());
	}
	
	@Test
	public void testGetStudent() {
		int studentId = 1;
		Student student = new Student();
		student.setStudentId(studentId);
		student.setFirstName("Juan");
		student.setLastName("Perez");
		
		when(studentRepository.findById(studentId).orElse(null)).thenReturn(student);
		assertEquals(student, studentServiceImpl.getStudent(studentId));
		
	}
	
	
	@Test
	public void testCreateStudent() {
		Student student = new Student();
		student.setStudentId(1);
		student.setFirstName("Juan");
		student.setLastName("Perez");
		
		
		when(studentRepository.save(student)).thenReturn(student);
		assertEquals(student, studentServiceImpl.createStudent(student));
		
	}
	
	@Test
	public void testModifyStudent() {
		Student student = new Student();
		student.setStudentId(1);
		student.setFirstName("Juan");
		student.setLastName("Perez");
		
		when(studentRepository.save(student)).thenReturn(student);
		assertEquals(student, studentServiceImpl.modifyStudent(student));
	}
	
	@Test
	public void testDeleteStudent() {
		studentServiceImpl.deleteStudent(9999);
		verify(studentRepository, times(1)).deleteById(9999);;
	}
}
