package com.myapp.shoppingmall.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

//실제 테이블과 매핑
@Entity
@Table(name = "products")
@Data  //겟,셋 생성자, toString 생성됨
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "품명을 입력해 주세요")
	@Size(min = 2, message = "품명은 2자 이상 입력해 주세요")
	private String name;
	
	private String slug;
	
	@NotBlank(message = "설명을 입력해 주세요")
	@Size(min = 5, message = "상품설명은 5자 이상 입력해 주세요")
	private String description;  //상품 설명
	
	private String image;		 //상품 이미지 주소
	
	@Pattern(regexp = "^[1-9][0-9]*") // 1~999999999 까지 사용가능
	private String price;  		 //가격 문자열로 하고 변환해서 사용
	
	@Pattern(regexp = "^[1-9][0-9]*", message = "카테고리를 선택해 주세요") //
	@Column(name = "category_id")
	private String categoryId;		//상품의 카테고리ID
	
	@Column(name = "created_at" , updatable = false) // 생성후 업데이트는 안됨
	@CreationTimestamp				//생성(insert)시 자동
	private LocalDateTime createdAt;	//상품 등록 시간
	
	@Column(name = "updated_at")	//업데이트(update)시 자동
	@UpdateTimestamp				
	private LocalDateTime updatedAt;	//상품 업데이트 타임

}
