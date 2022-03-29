package com.myapp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.pma.entities.Employee;
import com.myapp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public String diplayProjectList(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("empList", empList);
		return "employees/list-employees";
	}
		
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Employee e = new Employee();
		model.addAttribute("employee", e);
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createProject(Employee employee) {
		employeeService.save(employee); //DB에 employee객체를 테이블에 저장
		return "redirect:/employees/new"; //post-redirect-get 패턴
	}
	
}
