package com.myapp.pma;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


// 모든 컨트롤러에 적용 (모든 주소에 적용됨)
@ControllerAdvice
public class Common {

	//각 컨트롤러가 화면(뷰)에 보내는 모델 객체에 적용
	@ModelAttribute
	public void sharedData(Model model, Principal principal) {
		
		//Principal은 시큐리티 인증시 인증된 유저정보를 담고있는 객체이다.
		if(principal != null) {
			model.addAttribute("principal", principal.getName()); //인증 유저의 유저네임을 전달
		}
	}
}
