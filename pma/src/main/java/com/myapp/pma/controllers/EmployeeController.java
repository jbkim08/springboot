package com.myapp.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myapp.pma.entities.Employee;
import com.myapp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	/**
	 * 직원 리스트를 출력한다.
	 * @param model
	 * @return 직원 리스트 페이지
	 */
	@GetMapping
	public String diplayProjectList(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("empList", empList);
		return "employees/list-employees";
	}
	/**
	 * 새직원을 입력한다.	
	 * @param model
	 * @return 직원 만들기 페이지
	 */
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Employee e = new Employee();
		model.addAttribute("employee", e);
		return "employees/new-employee";
	}
	/**
	 * 직원 저장 또는 업데이트 한다.
	 * @param employee
	 * @return 직원 리스트 페이지
	 */
	@PostMapping("/save")
	public String createProject(@Valid Employee employee, Errors errors) {
		// 유효성 체크 실패시 에러발생시 => 입력 페이지로 되돌림
		if(errors.hasErrors()) return "employees/new-employee";
			
		Long id = employee.getEmployeeId();
		if(id != null) { //id가 있을 경우
			employeeService.update(employee); // 업데이트
		} else {
			employeeService.save(employee); //DB에 employee객체를 새로 저장
		}		
		return "redirect:/employees"; //post-redirect-get 패턴
	}
	/**
	 * 업데이트 창을 보여준다.
	 * @param id (직원id)
	 * @param model
	 * @return 직원 업데이트 페이지 (새로 만들기와 동일)
	 */
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long id, Model model) {
		// id로 DB에서 업데이트할 직원을 찾아서 화면에 표시하기
		Employee employee = employeeService.findByEmployeeId(id); //DB에서 찾기
		model.addAttribute("employee", employee);
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}
	
}
