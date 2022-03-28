package com.myapp.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
	
	@GetMapping("/basic")
	public String sayHello() {
		return "<h1>헬로우월드</h1>";
	}
	
	@GetMapping("/korean")
	public String translate() {
		return "<h1>안녕하세요</h1>";
	}
	
	@GetMapping("/japan")
	public String translate2() {
		return "<h1>곤니찌와</h1>";
	}
	
	@GetMapping("/form")
	public String form() {
		return "<form method=\"post\" action=\"/hello/formpost\">\r\n"
				+ "    이름 : <input type=\"text\" name=\"name\"><br>\r\n"
				+ "    학번 : <input type=\"text\" name=\"id\"><br>\r\n"
				+ "    학과 : <input type=\"text\" name=\"dep\"><br>\r\n"
				+ "    <input type=\"submit\">\r\n"
				+ "</form>";
	}
	
	@PostMapping("/formpost")
	public String formpost( @RequestParam("name") String name,
							@RequestParam("id") String id,
							@RequestParam("dep") String dep ) {
		return "당신의 이름은 "+name+ " 아이디는 "+id+" 학과는 " + dep;
	}
	
	@GetMapping("/orders/{id}")
	public String order(@PathVariable String id) {
		return "주문 아이디는 : " + id;
	}
	
}
