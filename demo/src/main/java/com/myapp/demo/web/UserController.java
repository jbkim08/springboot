package com.myapp.demo.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.demo.domain.Product;

@RestController
@RequestMapping("/user")   //http://localhost:8080/user
public class UserController {
	
	@GetMapping("/{id}")
	public String displayUserId(@PathVariable("id") String userId) {
		return "유저 아이디 : " + userId;
	}
	
	@GetMapping("/{id}/contact")
	public String displayContact(@PathVariable("id") String userId,
								 @RequestParam(value = "phone", defaultValue = "폰없음") String phone ) {
		return "유저 아이디 : " + userId + ", 연락처 : " + phone;
	}
	
	//리스트를 리턴해보자 => 제이슨
	@GetMapping("/{id}/items")
	public List<String> displayUserItems() {
		return Arrays.asList("가방","노트북","신발");
	}
	
	//객체를 리턴해보자 => 제이슨
	@GetMapping("/{id}/products")
	public List<Product> displayUserProducts() {
		return Arrays.asList(	new Product(1, "모자", 5000), 
								new Product(2, "신발", 15000), 
								new Product(3, "가방", 25000) );
	}
	
}
