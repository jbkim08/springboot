package com.myapp.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myapp.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	//자동으로 CRUD 객체 생성
	@Override
	List<Employee> findAll();
}
