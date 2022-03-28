package com.myapp.pma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmaApplication.class, args);
	}
	
	// 프로젝트 실행시 자동으로 DB에 입력하는 코드
//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//
//		};
//	}

}
