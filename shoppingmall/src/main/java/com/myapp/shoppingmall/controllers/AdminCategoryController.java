package com.myapp.shoppingmall.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.shoppingmall.dao.CategoryRepository;
import com.myapp.shoppingmall.entities.Category;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping
	public String index(Model model) {
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		return "admin/categories/index"; 
	}
}
