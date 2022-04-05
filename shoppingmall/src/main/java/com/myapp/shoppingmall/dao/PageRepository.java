package com.myapp.shoppingmall.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.shoppingmall.entities.Page;

public interface PageRepository extends JpaRepository<Page, Integer> {
	// 리스트<Page>로 리턴되는 findAll 등 여러 메소드 추가됨, 페이징 sorting
}
