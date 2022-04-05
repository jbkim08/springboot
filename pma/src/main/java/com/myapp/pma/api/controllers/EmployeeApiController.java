package com.myapp.pma.api.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.pma.dao.EmployeeRepository;
import com.myapp.pma.entities.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	
	@Autowired
	private EmployeeRepository empRepo;

//	@GetMapping
//	public Iterable<Employee> getEmployees(){
//		return empRepo.findAll();
//	}
	
	//페이징 적용 직원리스트
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Employee> findPaginatedEmployees(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "5") int size){
		//페이지설정 객체를 쓸려면 리파지토리를 pageAndsorting으로 수정
		Pageable pageable = PageRequest.of(page, size);
		return empRepo.findAll(pageable);
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
	
	//전체 업데이트  http put 메소드
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)  //http 상태 메세지 
	public Employee update(@RequestBody @Valid Employee employee) {
		return empRepo.save(employee); //save는 id가 있으면 업데이트 없으면 새로 생성
	}
	
	//부분 업데이트 
	@PutMapping(path = "/{id}", consumes = "application/json")
	public Employee update(@PathVariable("id") long id, @RequestBody Employee employee) {
		Employee emp = empRepo.findByEmployeeId(id); //업데이트 전에 데이터를 가져옴
		// 혹시 널값이 아니라면 (없으면 업데이트 안함 => 부분 업데이트)
		if(employee.getEmail() != null) {
			emp.setEmail(employee.getEmail());
		}
		if(employee.getFirstName() != null) {
			emp.setFirstName(employee.getFirstName());
		}
		if(employee.getLastName() != null) {
			emp.setLastName(employee.getLastName());
		}
		// 업데이트 된 emp를 저장하여 업데이트	
		return empRepo.save(emp);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		try {
			empRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("삭제되지 않음!"); //DB 삭제시 예외 발생
		}
	}
	

	
}





