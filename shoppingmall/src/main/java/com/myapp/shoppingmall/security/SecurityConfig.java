package com.myapp.shoppingmall.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


// 1.상속 WebSecurityConfigurerAdapter ,2 어노테이션 @EnableWebSecurity
@EnableWebSecurity
@Configuration  //클래스 안에 등록할 객체또는 메소드가 있음을 표시
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	//시큐리티는 1. 인증(로그인) 2.허가(role에 따른 허용가능한 범위)
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean  //이 메소드를 스프링에 빈(객체 메소드)로 등록
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(); //패스워드 인코더 객체
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 인증 메소드 구현 인증하기 위해 userDetailsService 로 유저의 username password role 등을 찾아서 인증
		auth
			.userDetailsService(userDetailsService) //유저나 관지자를 찾음
			.passwordEncoder(encoder());		    //패스워드를 다시 풀기위한 암호화 객체 필요
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 허가(role에 따른 허용가능한 범위)
		http.authorizeHttpRequests()
			.antMatchers("/category/**").hasAnyRole("USER", "ADMIN") // 카테고리는 유저,관리자 접근가능
			.antMatchers("/admin/**").hasAnyRole("ADMIN")			 // 관리자폴더는 관리자만
			.antMatchers("/").permitAll()							 // 누구나 접근 가능
				.and()
					.formLogin().loginPage("/login")			//인증 로그인 페이지 주소
				.and()
					.logout().logoutSuccessUrl("/")  			//로그아웃 후 홈으로 이동
				.and()
					.exceptionHandling().accessDeniedPage("/");  //예외 발생시 홈으로 이동
	}
}
