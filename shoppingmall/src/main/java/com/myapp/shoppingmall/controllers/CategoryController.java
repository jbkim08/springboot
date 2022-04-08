package com.myapp.shoppingmall.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myapp.shoppingmall.dao.CategoryRepository;
import com.myapp.shoppingmall.dao.ProductRepository;
import com.myapp.shoppingmall.entities.Category;
import com.myapp.shoppingmall.entities.Product;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	/**
	 * 입력된 slug 카테고리별로 제품리스트 표시(페이징 포함)
	 * @param slug 카테고리 slug
	 * @param page 표시할 페이지 번호
	 * @return products 페이지
	 */
	@GetMapping("/{slug}")
	public String category(@PathVariable String slug, Model model, @RequestParam(value = "page", defaultValue = "0") int page) {

		int perPage = 6; //한 페이지에 6개 출력 (최대 6개)
		Pageable pageable = PageRequest.of(page, perPage); // 표시할페이지, 한페이지에 몇개(6)
		long count = 0;
		
		//카테고리 선택 (all(전체) , 그외 개별 카테고리)
		if(slug.equals("all")) {
			Page<Product> products = productRepo.findAll(pageable);
			
			count = productRepo.count(); //전체 제품 수			
			model.addAttribute("products", products); // 전체 제품들
			
		} else { //카테고리별 페이징
			Category category = categoryRepo.findBySlug(slug);
			if(category == null) {
				return "redirect:/"; //카테고리가 없으면 홈으로
			}
			String categoryId = Integer.toString(category.getId());
			String categoryName = category.getName();
			List<Product> products = productRepo.findAllByCategoryId(categoryId, pageable);
			
			count = productRepo.countByCategoryId(categoryId);	
			model.addAttribute("products", products); //선택한 카테고리의 제품들
			model.addAttribute("categoryName", categoryName);
		}
		
		// 페이지를 보여주기 위해 계산
		double pageCount = Math.ceil( (double)count / (double)perPage ); // 13 / 6 = 2.12 (총페이지숫자)
		
		model.addAttribute("pageCount", (int)pageCount); //총페이지
		model.addAttribute("perPage", perPage);			//페이지당 표시 아이템수
		model.addAttribute("count", count);				//총 아이템 갯수
		model.addAttribute("page", page);				//현재 페이지
		
		return "products"; //produts.html 페이지로
	}
}
