package com.myapp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.pma.entities.Employee;
import com.myapp.pma.entities.Project;
import com.myapp.pma.services.EmployeeService;
import com.myapp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	//스프링에서 repository 객체를 처음에 자동생성해서 가지고 있다가
	//오토와이어는 관련 객체를 필요할때 자동으로 연결해준다.
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public String diplayProjectList(Model model) {
		List<Project> projectList = projectService.findAll();
		model.addAttribute("projectList", projectList);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project p = new Project();
		model.addAttribute("project", p);
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("empList", empList);
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project) {
		projectService.save(project); //DB에 project객체를 테이블에 저장
		return "redirect:/projects/new"; //post-redirect-get 패턴
	}
}
