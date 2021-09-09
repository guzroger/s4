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

import com.rguzman.s4.model.Student;
import com.rguzman.s4.rest.response.ServiceResponse;
import com.rguzman.s4.service.StudentService;


@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/student")
	public ResponseEntity<ServiceResponse> createStudent(@RequestBody Student studentParameter, HttpServletRequest request) 
	{
		ServiceResponse serviceResponse = new ServiceResponse();
		
		if(studentParameter.getFirstName()!=null && studentParameter.getLastName()!=null)
		{
			try {
				studentService.createStudent(studentParameter);
			
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
	
	@PutMapping("/student/{studentId}")
	public ResponseEntity<ServiceResponse> modifyStudent(@PathVariable(value="studentId") Integer studentId, @RequestBody Student studentParameter, HttpServletRequest request) 
	{
		ServiceResponse serviceResponse = new ServiceResponse();
		
		if(studentParameter.getFirstName()!=null && studentParameter.getLastName()!=null)
		{
			try {
				
				studentParameter.setStudentId(studentId);
				studentService.modifyStudent(studentParameter);
			
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
	
	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<ServiceResponse> deleteStudent(@PathVariable(value="studentId") Integer studentId, HttpServletRequest request) 
	{
		ServiceResponse serviceResponse = new ServiceResponse();
		
		if(studentId!=null)
		{
			try {
				studentService.deleteStudent(studentId);
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
	
	@GetMapping("/student/{studentId}")
	public ResponseEntity<Student> getStudent(@PathVariable(value="studentId") Integer studentId, HttpServletRequest request) 
	{
		
		Student student = studentService.getStudent(studentId);
		
		
		if(student!=null)
			return new ResponseEntity<>(student, HttpStatus.OK);
		else
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/student")
	public ResponseEntity<List<Student>> listStudent( HttpServletRequest request) 
	{
		
		return new ResponseEntity<>(studentService.listStudents(), HttpStatus.OK);
	}
	
	
}
