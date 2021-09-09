package com.rguzman.s4.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rguzman.s4.model.Class_;
import com.rguzman.s4.rest.response.ServiceResponse;
import com.rguzman.s4.service.ClassService;


@RestController
public class ClassController {
	
	@Autowired
	private ClassService classService;
	
	@PostMapping("/class")
	public ResponseEntity<ServiceResponse> createClass(@RequestBody Class_ classParameter, HttpServletRequest request) 
	{
		ServiceResponse serviceResponse = new ServiceResponse();
		
		if(classParameter.getCode()!=null && classParameter.getTitle()!=null)
		{
			try {
				classService.createClass(classParameter);
			
				serviceResponse.setErrorCode("200");
				serviceResponse.setErrorMessage("OK");
			}
			catch (Exception e) {
				serviceResponse.setErrorCode("500");
				serviceResponse.setErrorMessage(e.getMessage());
				return new ResponseEntity<>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			serviceResponse.setErrorCode("400");
			serviceResponse.setErrorMessage("Parameter error");
			return new ResponseEntity<>(serviceResponse, HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/class/{code}")
	public ResponseEntity<ServiceResponse> modifyClass(@PathVariable(value="code") String code, @RequestBody Class_ classParameter, HttpServletRequest request) 
	{
		ServiceResponse serviceResponse = new ServiceResponse();
		
		if(classParameter.getTitle()!=null)
		{
			try {
				
				classParameter.setCode(code);
				classService.modifyClass(classParameter);
			
				serviceResponse.setErrorCode("200");
				serviceResponse.setErrorMessage("OK");
			}
			catch (Exception e) {
				serviceResponse.setErrorCode("500");
				serviceResponse.setErrorMessage(e.getMessage());
				return new ResponseEntity<>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			serviceResponse.setErrorCode("400");
			serviceResponse.setErrorMessage("Parameter error");
			return new ResponseEntity<>(serviceResponse, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(serviceResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/class/{code}")
	public ResponseEntity<ServiceResponse> deleteStudent(@PathVariable(value="code") String code, HttpServletRequest request) 
	{
		ServiceResponse serviceResponse = new ServiceResponse();
		
		if(code!=null)
		{
			try {
				classService.deleteClass(code);
			}
			catch (Exception e) {
				serviceResponse.setErrorCode("500");
				serviceResponse.setErrorMessage(e.getMessage());
				return new ResponseEntity<>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			serviceResponse.setErrorCode("400");
			serviceResponse.setErrorMessage("Parameter error");
			return new ResponseEntity<>(serviceResponse, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(serviceResponse, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/class/{code}")
	public ResponseEntity<Class_> getStudent(@PathVariable(value="code") String code, HttpServletRequest request) 
	{
		
		Class_ class_ = classService.getClass(code);
		
		
		if(class_!=null)
			return new ResponseEntity<>(class_, HttpStatus.OK);
		else
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/class")
	public ResponseEntity<List<Class_>> listStudent( HttpServletRequest request) 
	{
		
		return new ResponseEntity<>(classService.listClasses(), HttpStatus.OK);
	}
	
	
}
