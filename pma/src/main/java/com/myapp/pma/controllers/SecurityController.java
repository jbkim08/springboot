package com.myapp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.myapp.pma.dao.UserAccountRepository;
import com.myapp.pma.entities.UserAccount;

@Controller
public class SecurityController {
	
	@Autowired
	UserAccountRepository userRepo;
	
	//암호화는 유저를 저장할때 또, 유저를 인증할때도 필요하다.
	@Autowired
	BCryptPasswordEncoder bEncoder;
	
	//가입하기 화면 표시
	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();   // 빈 유저 객체 생성
		model.addAttribute("userAccount", userAccount); // 아래 화면에 매핑
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) {
		//비밀번호 암호화
		user.setPassword(bEncoder.encode(user.getPassword()));
		//user.setRole("ROLE_USER");
		//user.setEnabled(true);
		userRepo.save(user);
		return "redirect:/";
	}

}





