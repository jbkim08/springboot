package com.myapp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.pma.dao.EmployeeRepository;
import com.myapp.pma.dto.EmployeeProject;
import com.myapp.pma.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public void save(Employee employee) {
		employeeRepository.save(employee);	
	}

	public List<EmployeeProject> employeeProjects() {		
		return employeeRepository.employeeProjects();
	}

	public Employee findByEmployeeId(long id) {		
		return employeeRepository.findByEmployeeId(id);
	}

}
