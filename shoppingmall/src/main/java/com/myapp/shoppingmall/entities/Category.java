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
@Table(name = "categories")
@Data  //겟,셋 생성자, toString 생성됨
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "이름을 입력해 주세요.")
	@Size(min = 2, message = "이름은 2자 이상")
	private String name;
	
	private String slug;
	
	private int sorting;
}
