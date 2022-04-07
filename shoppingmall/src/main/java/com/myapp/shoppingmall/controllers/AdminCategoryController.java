package com.myapp.shoppingmall.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.shoppingmall.dao.CategoryRepository;
import com.myapp.shoppingmall.entities.Category;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping
	public String index(Model model) {
		List<Category> categories = categoryRepo.findAllByOrderBySortingAsc();
		model.addAttribute("categories", categories);
		return "admin/categories/index"; 
	}
	
	@GetMapping("/add") 
	public String add(@ModelAttribute Category category) {
		return "admin/categories/add";
	}
	
	@PostMapping("/add")
	public String add(@Valid Category category, BindingResult bindingResult, RedirectAttributes attr) {
		// 유효성검사 결과 에러가 있으면 다시 돌아감
		if (bindingResult.hasErrors())
			return "admin/categories/add";
		// 검사 통과시
		attr.addFlashAttribute("message", "성공적으로 페이지 추가됨");
		attr.addFlashAttribute("alertClass", "alert-success"); // 부트스트랩 경고창(성공색)

		String slug = category.getName().toLowerCase().replace(" ", "-");
		
		Category nameExist = categoryRepo.findByName(category.getName()); // 이름 DB검색하여 있으면 Category로 리턴

		if (nameExist != null) { // 같은 이름의 카테고리가 있을경우
			attr.addFlashAttribute("message", "카테고리가 이미 있습니다. 다른것을 고르세요");
			attr.addFlashAttribute("alertClass", "alert-danger"); // 부트스트랩 경고창(경고색)
			attr.addFlashAttribute("category", category); // 부트스트랩 경고창(경고색)
		} else {
			category.setSlug(slug); // 소문자 - 수정된 슬러그를 업데이트
			category.setSorting(100); // 기본 솔팅값

			categoryRepo.save(category);
		}

		return "redirect:/admin/categories/add"; // post-redirect-get
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		Category category = categoryRepo.getById(id); 
		model.addAttribute("category", category); 
		return "admin/categories/edit"; // 수정 페이지
	}
	
	@PostMapping("/edit")
	public String edit(@Valid Category category, BindingResult bindingResult, RedirectAttributes attr) {

		if(bindingResult.hasErrors()) {
			return "admin/categories/edit";
		}
		// 성공적으로 수정 되는 경우
		attr.addFlashAttribute("message", "카테고리 성공적 수정!");
		attr.addFlashAttribute("alertClass", "alert-success");
		
		String slug = category.getName().toLowerCase().replace(" ", "-");
		
		Category categoryExists = categoryRepo.findByName(category.getName()); //같은 카테고리가 있는지 검사
		
		if(categoryExists != null) {
			attr.addFlashAttribute("message", "카테고리가 이미 있습니다. 다른것을 고르세요");
			attr.addFlashAttribute("alertClass", "alert-danger");
			attr.addFlashAttribute("category", category); //입력내용을 보여주기위함
		} else {
			category.setSlug(slug);  //슬러그를 - 형식으로 수정해 저장
			category.setSorting(100); //기본 솔팅값
			
			categoryRepo.save(category);
		}
		return "redirect:/admin/categories/edit/" + category.getId();
	}
		
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
		
		categoryRepo.deleteById(id);     // id로 삭제하는 메서드
		
		redirectAttributes.addFlashAttribute("message", "성공적으로 삭제 되었습니다.");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");		
		
		return "redirect:/admin/categories"; //인덱스 페이지로 되돌아감
	}
	
	@PostMapping("/reorder")
	public @ResponseBody String reorder(@RequestParam("id[]") int[] id) {
		
		int count = 1;
		Category category;
		
		for(int categoryId : id) {
			category = categoryRepo.getById(categoryId); //DB에서 id로 category 객체 검색
			category.setSorting(count);
			categoryRepo.save(category); //sorting값을 넣고 저장한다.
			count++;
		}
		
		return "ok"; //뷰페이지가 아니고 ok문자열로 리턴됨
	}
}
