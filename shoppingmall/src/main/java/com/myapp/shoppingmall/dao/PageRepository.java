package com.myapp.shoppingmall.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.shoppingmall.entities.Page;

public interface PageRepository extends JpaRepository<Page, Integer> {
	// 리스트<Page>로 리턴되는 findAll 등 여러 메소드 추가됨, 페이징 sorting	
	Page findBySlug(String slug); // 실제 구현은 jpa 하이버네이트가 함

	Page findBySlugAndIdNot(String slug, int id); // 슬러그를 찾는데 현재 id로 찾은것은 제외

	List<Page> findAllByOrderBySortingAsc();
}
