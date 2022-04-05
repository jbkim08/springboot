package com.myapp.shoppingmall;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 컨트롤러 대신 뷰를 매핑 가능
		registry.addViewController("/").setViewName("home");
	}
}
