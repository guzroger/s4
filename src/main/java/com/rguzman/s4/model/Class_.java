package com.rguzman.s4.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="class")
public class Class_ {
	
	@Id
	@Column(name="code",  nullable = false)
	private String code;
	@Column(name="title", nullable = false)
	private String title;
	@Column(name="description")
	private String description;
	
	
	@JsonIgnoreProperties("classes")
	@ManyToMany(mappedBy = "classes",fetch = FetchType.LAZY)
	private List<Student> students;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
}
