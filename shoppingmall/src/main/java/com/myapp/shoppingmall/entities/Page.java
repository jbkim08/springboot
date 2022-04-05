package com.myapp.shoppingmall.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

//실제 테이블과 매핑
@Entity
@Table(name = "pages")
@Data  //겟,셋 생성자, toString 생성됨
public class Page {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "제목을 입력해 주세요.")
	@Size(min = 2, message = "제목은 2자 이상")
	private String title;	
	private String slug; //title을 소문자 뛰워쓰기 특수문자등을 - 바꿈
	
	@NotBlank(message = "내용을 입력해 주세요.")
	@Size(min = 5, message = "내용은 5자 이상")
	private String content;
	private int sorting; //정렬 순서

}
