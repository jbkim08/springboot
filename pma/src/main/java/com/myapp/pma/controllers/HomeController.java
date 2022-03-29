package com.myapp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.myapp.pma.dto.EmployeeProject;
import com.myapp.pma.entities.Project;
import com.myapp.pma.services.EmployeeService;
import com.myapp.pma.services.ProjectService;

@Controller
public class HomeController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/")
	public String displayHome(Model model) {
		List<Project> projectList = projectService.findAll();
		List<EmployeeProject> empProList = employeeService.employeeProjects();
		model.addAttribute("projectList", projectList);
		model.addAttribute("empProList", empProList);
		return "main/home";
	}
}
