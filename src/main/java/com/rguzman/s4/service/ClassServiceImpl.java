package com.rguzman.s4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rguzman.s4.model.ClassRepository;
import com.rguzman.s4.model.Class_;


@Service
public class ClassServiceImpl implements ClassService {
	
	@Autowired
	private ClassRepository classRepository;
	
	

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

	
	

}
