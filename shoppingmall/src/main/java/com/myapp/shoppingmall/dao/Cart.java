package com.myapp.shoppingmall.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //get set 메소드 toString  자동생성 
@AllArgsConstructor //전체 생성자
public class Cart {
	
	private int id;			//제품id
	private String name;	//제품이름
	private String price;	//제품가격
	private int quantity;	//제품수량
	private String image;	//제품이미지
	
}
