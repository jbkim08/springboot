package com.myapp.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//1.시큐리티 설정을 위해서 WebSecurityConfigurerAdapter 상속
//2.어노테이션 EnableWebSecurity
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource; //데이터 베이스 
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; //패스워드 인코딩 객체
	
	// 3.인증	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// DB user_accounts에  유저, 비밀번호, roles(허용범위 유저,관리자 등)
		auth.jdbcAuthentication()
			.usersByUsernameQuery("select username, password, enabled from user_accounts where username = ?")
			.authoritiesByUsernameQuery("select username,role from user_accounts where username = ?")
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder); //암호화된 패스워드를 다시 디코딩해서 입력된 비번과 비교
	}
	
	
	// 4.허가
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http.authorizeRequests()
			//.antMatchers("/projects/new").hasRole("ADMIN")   //관리자
			//.antMatchers("/projects/save").hasRole("ADMIN")
			//.antMatchers("/employees/new").hasRole("ADMIN") 
			//.antMatchers("/employees/save").hasRole("ADMIN")
			//.antMatchers("/employees").authenticated()       //인증된 유저
			//.antMatchers("/projects").authenticated()
			.antMatchers("/","/**").permitAll()				     //아무나
			.and()
			.formLogin(form -> form.loginPage("/login").permitAll())         //커스텀 로그인 페이지 추가
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")); //로그아웃 추가
			 
		//시큐리티에서는 기본적으로 csrf 방지가 적용중 rest-api에서는 끄기
		http.csrf().disable();												
	}
}
