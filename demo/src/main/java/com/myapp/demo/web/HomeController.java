package com.myapp.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.demo.domain.User;

@Controller
public class HomeController {

	// 뷰와 컨트롤러 사이의 데이터를 Model을 통해 전달	
	@GetMapping("/")
	public String home(Model model) {
		//	"user"이름으로 User의 빈객체를 넣어서 "user" = new User();	
		model.addAttribute("user", new User());
		// model을 통해 index.html페이지에 전달
		return "index";
	}
	
	@PostMapping("/create")
	public String processFormData(User user, RedirectAttributes attr) {
		//System.out.println(user.toString());
		attr.addFlashAttribute("user", user);
		return "redirect:/display"; //display 페이지로 새로운 요청
	}
	
	@GetMapping("/display")
	public String displayFormDate(User user) {
		return "result";
	}
}
