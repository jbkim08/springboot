package com.myapp.bbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.myapp.bbs.model.User;

/* 새 클래스를 만들고 인터페이스 HandlerInterceptor를 구현하여 인터셉터 만들기 */
/* 로그인 한후 계속해서 인증상태인지 확인 */
public class LoginCheckInterceptor implements HandlerInterceptor {
	
	/* preHandle 메소드 : 컨트롤러에 가기 전 Interceptor에서 캐치해서 작업 수행 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(); //세션 불러오기
		User user = (User) session.getAttribute("user");
		if(user == null) {
			//인증이 안된 상태 => 로그인 화면으로 이동
			String url = session.getServletContext().getContextPath() + "/login";
			response.sendRedirect(url); //리다이렉트 요청 로그인
			System.out.println("LoginInterceptor # preHandle() : 로그인 안됨");
			return false;
		}
		
		// 인터셉터 메소드에서 리턴이 true면 통과 false면 차단
		System.out.println("LoginInterceptor # preHandle() : 통과됨");
		return true;
	}

}
