package com.myapp.pma.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 에러 페이지 컨트롤러는 에러컨트롤러를 구현해야 한다.
@Controller
public class AppErrorController implements ErrorController {

	//에러 발생시 주소가 "/error"로 들어온다.
	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		
		// 에러 상태 코드 확인		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(status != null) { // 에러가 맞을시
			Integer statusCode = Integer.valueOf(status.toString()); // 403, 404, 500
			
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "errorpages/404";
			}
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "errorpages/500";
			}
			else if(statusCode == HttpStatus.FORBIDDEN.value()) {
				return "errorpages/403";
			}
		}
		// 위의 에러상태가 아닐경우 그냥 error 페이지로 이동
		return "errorpages/error";
	}

}
