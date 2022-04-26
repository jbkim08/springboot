package com.myapp.bbs;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.myapp.bbs.model.User;

@ControllerAdvice
public class Common {
	
	@ModelAttribute
	public void sharedData(Model model, HttpSession session) {
		// 세션의 인증된 유저가 있으면 유저 이름을 모든 페이지에 전달
		User user = (User)session.getAttribute("user");
		
		if(user != null) {
			model.addAttribute("userName", user.getName());
		}
	}

}
