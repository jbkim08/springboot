package com.myapp.bbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.bbs.model.Login;
import com.myapp.bbs.model.User;
import com.myapp.bbs.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
	public String getLoginView(@ModelAttribute Login login) {
		//로그인 뷰 페이지에 Login객체 login을 바인딩
		return "login";
	}
	
	/**
	 * 로그인 처리 (인증 되었을 경우에 세션에 유저 저장) 
	 * @param login
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/login")
	public String postLogin(Login login, Model model, HttpSession session) {

		loginService.authenticate(login); //인증 메소드 실행(실패시 에러메세지 입력됨)
		if (login.getError() != null) {
			model.addAttribute("message", login.getError());
			return "login"; //로그인 실패
		} else {
			User user = loginService.findUserByEmail(login.getEmail());
			session.setAttribute("user", user); //세션에 인증된 유저를 저장함
			//System.out.println(user.toString()); //유저 출력
			return "redirect:/board/list";
		}
	}
	
	@GetMapping("/logout")
	public String getLogout(HttpSession session, RedirectAttributes attr) {
		session.invalidate(); //모든 세션 삭제
		//session.removeAttribute("user"); 유저만 삭제
		attr.addFlashAttribute("message", "로그아웃 됨");
		return "redirect:/login"; //로그인 페이지로
	}


}
