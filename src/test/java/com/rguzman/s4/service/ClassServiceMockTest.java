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

import com.rguzman.s4.model.ClassRepository;
import com.rguzman.s4.model.Class_;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassServiceMockTest {
	
	@Autowired
	private ClassServiceImpl classServiceImpl;
	
	@MockBean
	private ClassRepository classRepository;
	
	
	@Test
	public void testListClass() {
		when(classRepository.findAll()).thenReturn(Stream.of(new Class_(), new Class_()).collect(Collectors.toList()) );
		assertEquals(2, classServiceImpl.listClasses().size());
	}
	
	@Test
	public void testGetClass() {
		String code = "MAT111";
		Class_ class_ = new Class_();
		class_.setCode(code);;
		class_.setTitle("MATEMATICAS");
		
		when(classRepository.findById(code).orElse(null)).thenReturn(class_);
		assertEquals(class_, classServiceImpl.getClass(code));
		
	}
	
	@Test
	public void testCreateClass() {
		Class_ class_ = new Class_();
		class_.setCode("MAT111");;
		class_.setTitle("MATEMATICAS");
		
		when(classRepository.save(class_)).thenReturn(class_);
		assertEquals(class_, classServiceImpl.createClass(class_));
		
	}
	
	@Test
	public void testModifyClass() {
		Class_ class_ = new Class_();
		class_.setCode("MAT111");;
		class_.setTitle("MATEMATICAS");
		
		when(classRepository.save(class_)).thenReturn(class_);
		assertEquals(class_, classServiceImpl.modifyClass(class_));
	}
	
	@Test
	public void testDeleteClass() {
		classServiceImpl.deleteClass("MAT");
		verify(classRepository, times(1)).deleteById("MAT");;
	}
}
