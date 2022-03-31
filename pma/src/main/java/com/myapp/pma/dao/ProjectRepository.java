package com.myapp.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myapp.pma.entities.Project;

//JAP 에서는 리파지토리에 CRUD 가능한 인터페이스 CrudRepository를 상속 

public interface ProjectRepository extends CrudRepository<Project, Long> {
	//CrudRepository에 이미 CRUD 메소드가 다 만들어져 있음. => JPA 하이버네이트가 구현 코드도 다 자동생성
	@Override
	List<Project> findAll(); //기존의 findAll() 리턴타입이 Iterable<Project>이라서 수정

	Project findByProjectId(long id);
	
}
