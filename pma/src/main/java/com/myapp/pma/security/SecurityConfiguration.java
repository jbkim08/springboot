package com.myapp.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//시큐리티 설정을 위해서 WebSecurityConfigurerAdapter 상속
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	// 인증	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 메모리에 유저, 비밀번호, roles(허용범위 유저,관리자 등)
		auth.inMemoryAuthentication()
			.withUser("drv98").password( getPasswordEncoder().encode("1234") ).roles("USER")
			.and()
			.withUser("admin").password( getPasswordEncoder().encode("pass") ).roles("ADMIN");
	}
	
	@Bean   //패스워드 암호화 객체
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance(); //테스트용 암호화(x)
	}	
	
	// 허가
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http.authorizeHttpRequests()
			.antMatchers("/projects/new").hasRole("ADMIN")   //관리자
			.antMatchers("/projects/save").hasRole("ADMIN")
			.antMatchers("/employees/new").hasRole("ADMIN") 
			.antMatchers("/employees/save").hasRole("ADMIN")
			.antMatchers("/employees").authenticated()       //인증된 유저
			.antMatchers("/projects").authenticated()
			.antMatchers("/").permitAll()				     //아무나
			.and().formLogin();	
		//시큐리티에서는 기본적으로 csrf 방지가 적용중
		//http.csrf().disable();												
	}
}
