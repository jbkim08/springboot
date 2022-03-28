package com.myapp.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Employee {
	
	@Id  //기본키를 명시
	@GeneratedValue(strategy = GenerationType.AUTO) //자동생성 
	private Long employeeId;
	
	private String firstName;
	private String lastName;
	private String email;
	
	//다 : 다 관계에서는 테이블을 만들고 이테이블에 id를 넣고 다른 테이블의 아이디도 입력
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, 
			CascadeType.REFRESH}, fetch = FetchType.LAZY)  
	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name="employee_id"),
					inverseJoinColumns = @JoinColumn(name="project_id"))
	private List<Project> projects;
	
	// 빈 객체 생성	
	public Employee() {
	}
	
	// id 제외한 객체 생성	
	public Employee(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
