package com.myapp.shoppingmall.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.shoppingmall.dao.CategoryRepository;
import com.myapp.shoppingmall.dao.ProductRepository;
import com.myapp.shoppingmall.entities.Category;
import com.myapp.shoppingmall.entities.Product;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping
	public String index(Model model) {
		
		List<Product> products = productRepo.findAll();
		
		model.addAttribute("products", products);
		
		return "admin/products/index";
	}
	
	@GetMapping("/add")
	public String add(@ModelAttribute Product product, Model model) {
		
		List<Category> categories = categoryRepo.findAll();
		
		model.addAttribute("categories", categories);
		//상품을 추가하는 add 페이지에 제품객체와 제품의 카테고리를 선택할수 있도록 카테고리 리스트 전달		
		return "admin/products/add"; 
	}

}
