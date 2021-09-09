package com.rguzman.s4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="student_class")
public class StudentClass {
	@Id
	@Column(name="student_id",  nullable = false)
	private Integer studentId;	
	@Column(name="code",  nullable = false)
	
	private String code;
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
