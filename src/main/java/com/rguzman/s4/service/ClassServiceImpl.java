package com.rguzman.s4.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rguzman.s4.model.ClassRepository;
import com.rguzman.s4.model.Class_;
import com.rguzman.s4.model.Student;
import com.rguzman.s4.model.StudentClass;
import com.rguzman.s4.model.StudentClassRepository;


@Service
public class ClassServiceImpl implements ClassService {
	
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private StudentClassRepository studentClassRepository;

	@Override
	public List<Class_> listClasses() {
		return classRepository.findAll();
	}

	@Override
	public Class_ getClass(String code) {
		return classRepository.findById(code).orElse(null);
	}

	@Override
	public Class_ createClass(Class_ class_) {
		return classRepository.save(class_);
		
	}

	@Override
	public Class_ modifyClass(Class_ class_) {
		return classRepository.save(class_);
		
	}

	@Override
	public void deleteClass(String code) {
		classRepository.deleteById(code);;
		
	}
	
	@Override
	public List<Student> listStudents(String code){
		Class_ class_ = classRepository.findById(code).orElse(null);
		if(class_!= null)
		{
			return class_.getStudents();
		}
		else
			return new ArrayList<Student>();
	}
	
	@Override
	public Class_ assignStudents(String code,  List<Integer> students) {
		
		for(Integer studentId:students)
		{
			StudentClass studentClass = new StudentClass();
			studentClass.setCode(code);
			studentClass.setStudentId(studentId);
			
			studentClassRepository.save(studentClass);
		}
		
		
		Class_ class_ = classRepository.findById(code).orElse(null);
		
		
		return class_;
		
	}

}
