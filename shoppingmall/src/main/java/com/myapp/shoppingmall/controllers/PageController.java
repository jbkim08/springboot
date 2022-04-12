package com.myapp.shoppingmall.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.shoppingmall.dao.PageRepository;
import com.myapp.shoppingmall.entities.Page;

/**
 * 
 * 쇼핑몰 기본 홈페이지
 *
 */
@Controller
@RequestMapping("/")  
public class PageController {

	@Autowired
	private PageRepository pageRepo;
	
	@GetMapping
	public String home(Model model) {
		//entity 페이지, home 슬러그의 페이지를 가져와서 전달
		Page page = pageRepo.findBySlug("home");
		model.addAttribute("page", page);		
		return "page"; // page.html로 이동
	}
	
	@GetMapping("/{slug}")
	public String page(@PathVariable String slug, Model model) {
		
		Page page = pageRepo.findBySlug(slug);
		
		if(page == null) {
			return "redirect:/"; //페이지가 없으면 홈으로 이동
		}
		model.addAttribute("page", page);	
		
		return "page";
	}
	
	//로그인 페이지 보여주기
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
}







