package com.myapp.shoppingmall;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.myapp.shoppingmall.dao.CategoryRepository;
import com.myapp.shoppingmall.dao.PageRepository;
import com.myapp.shoppingmall.entities.Category;
import com.myapp.shoppingmall.entities.Page;

//모든 컨트롤러에 적용(모든 페이지에 적용됨)
@ControllerAdvice
public class Common {
	
	@Autowired
	private PageRepository pageRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	//모델에 추가한다.
	@ModelAttribute
	public void sharedData(Model model) {
		// cpages에 모든 페이지들을 순서대로 담아서 전달
		List<Page> cpages = pageRepo.findAllByOrderBySortingAsc();
		List<Category> categories = categoryRepo.findAll(); 
		
		model.addAttribute("cpages", cpages);
		model.addAttribute("ccategories", categories);
	}

}
