package com.myapp.shoppingmall.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.shoppingmall.dao.UserRepository;
import com.myapp.shoppingmall.entities.User;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	//유저 리파지토리 , 패스워드 암호화
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public String register(User user) {
		return "register"; //가입하기 화면 뷰 보여주기
	}
	
	@PostMapping
	public String register(@Valid User user, BindingResult bindingResult, Model model) {
		
		//1. 유효성 검사 틀릴경우 되돌아감
		if(bindingResult.hasErrors()) {
			return "register";  
		}
		//2. 패스워드 확인 틀릴경우 되돌아감
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			model.addAttribute("passwordNotMatch", "패스워드확인이 틀림");
			return "register";
		}
		//3. 패스워드를 암호화 해서 입력함
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//4. DB에 새 유저 저장
		userRepo.save(user);
		
		return "redirect:/login"; // 가입하기 끝나고 로그인 페이지로 
	}
	
}





