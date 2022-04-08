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

import com.myapp.shoppingmall.dao.PageRepository;
import com.myapp.shoppingmall.entities.Page;

@Controller
@RequestMapping("/admin/pages")
public class AdminPageController {

	@Autowired
	private PageRepository pageRepo;

	@GetMapping
	public String index(Model model) {
		List<Page> pages = pageRepo.findAllByOrderBySortingAsc();
		model.addAttribute("pages", pages);
		return "admin/pages/index";
	}

	@GetMapping("/add") // /admin/pages/add
	public String add(@ModelAttribute Page page) {
		// model.addAttribute("page", new Page());
		return "admin/pages/add";
	}

	@PostMapping("/add")
	public String add(@Valid Page page, BindingResult bindingResult, RedirectAttributes attr) {
		// 유효성검사 결과 에러가 있으면 다시 돌아감
		if (bindingResult.hasErrors())
			return "admin/pages/add";
		// 검사 통과시
		attr.addFlashAttribute("message", "성공적으로 페이지 추가됨");
		attr.addFlashAttribute("alertClass", "alert-success"); // 부트스트랩 경고창(성공색)

		// 슬러그 검사(슬러그 미 입력시 제목을 소문자로하고 공백은 -으로 대체), 입력시에도 소문자 공백은 -대체
		String slug = (page.getSlug() == "") ? page.getTitle().toLowerCase().replace(" ", "-") : page.getSlug();
		Page slugExist = pageRepo.findBySlug(slug); // 슬러그로 DB검색하여 있으면 Page로 리턴

		if (slugExist != null) { // 같은 슬러그가 있을시 저장 안됨
			attr.addFlashAttribute("message", "슬러그가 이미 있습니다. 다른것을 고르세요");
			attr.addFlashAttribute("alertClass", "alert-danger"); // 부트스트랩 경고창(경고색)
			attr.addFlashAttribute("page", page); // 부트스트랩 경고창(경고색)
		} else {
			page.setSlug(slug); // 소문자 - 수정된 슬러그를 업데이트
			page.setSorting(100); // 기본 솔팅값

			pageRepo.save(page);
		}

		return "redirect:/admin/pages/add"; // post-redirect-get
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		Page page = pageRepo.getById(id); // 테이블에서 id로 page 검색
		model.addAttribute("page", page); // 수정페이지에 page 정보객체를 전달
		return "admin/pages/edit"; // 수정 페이지
	}

	@PostMapping("/edit")
	public String edit(@Valid Page page, BindingResult bindingResult, RedirectAttributes attr) {
		//유효성검사 결과 에러가 있으면 다시 돌아감
		if (bindingResult.hasErrors()) return "admin/pages/edit";
		//검사 통과시 
		attr.addFlashAttribute("message", "성공적으로 페이지 수정됨");
		attr.addFlashAttribute("alertClass", "alert-success"); // 부트스트랩 경고창(성공색)

		//슬러그 검사(슬러그 미 입력시 제목을 소문자로하고 공백은 -으로 대체), 입력시에도 소문자 공백은 -대체
		String slug = (page.getSlug() == "") ? page.getTitle().toLowerCase().replace(" ", "-") : page.getSlug();
		Page slugExist = pageRepo.findBySlugAndIdNot(slug, page.getId()); // 슬러그로 DB검색하여 있으면 Page로 리턴(현재 id의 슬러그는 제외)

		if (slugExist != null) { // 같은 슬러그가 있을시 저장 안됨
			attr.addFlashAttribute("message", "슬러그가 이미 있습니다. 다른것을 고르세요");
			attr.addFlashAttribute("alertClass", "alert-danger"); // 부트스트랩 경고창(경고색)
			attr.addFlashAttribute("page", page); 
		} else {
			page.setSlug(slug); // 소문자 - 수정된 슬러그를 업데이트

			pageRepo.save(page);
		}

		return "redirect:/admin/pages/edit/" + page.getId(); // post-redirect-get
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
		
		pageRepo.deleteById(id);     // id로 삭제하는 메서드
		
		redirectAttributes.addFlashAttribute("message", "성공적으로 삭제 되었습니다.");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");		
		
		return "redirect:/admin/pages"; //인덱스 페이지로 되돌아감
	}
	
	@PostMapping("/reorder")
	public @ResponseBody String reorder(@RequestParam("id[]") int[] id) {
		
		int count = 1;
		Page page;
		
		for(int pageId : id) {
			page = pageRepo.getById(pageId); //DB에서 id로 page 객체 검색
			page.setSorting(count);
			pageRepo.save(page); //sorting값을 넣고 저장한다.
			count++;
		}
		
		return "ok"; //뷰페이지가 아니고 ok문자열로 리턴됨
	}

}







