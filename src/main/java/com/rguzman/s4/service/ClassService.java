package com.rguzman.s4.service;

import java.util.List;

import com.rguzman.s4.model.Class_;
import com.rguzman.s4.model.Student;

public interface ClassService {
	public List<Class_> listClasses();
	public Class_ getClass(String code);
	public Class_ createClass(Class_ class_);
	public Class_ modifyClass(Class_ class_);
	public void deleteClass(String code);
	public List<Student> listStudents(String code);
	public Class_ assignStudents(String code, List<Integer> students);
	
}
