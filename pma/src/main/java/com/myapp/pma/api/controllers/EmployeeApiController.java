package com.myapp.pma.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.pma.dao.EmployeeRepository;
import com.myapp.pma.entities.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	
	@Autowired
	private EmployeeRepository empRepo;

	@GetMapping
	public Iterable<Employee> getEmployees(){
		return empRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id){
		return empRepo.findByEmployeeId(id);
	}
	
	// 요청하는 body에 json타입의 새 직원 데이터를 입력시 새로 직원 생성하고 그 직원을 리턴
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)   //상태 201 생성
	public Employee create(@RequestBody Employee employee){		
		return empRepo.save(employee);
	}
	
}
