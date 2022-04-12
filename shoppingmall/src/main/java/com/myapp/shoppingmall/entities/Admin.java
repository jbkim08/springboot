package com.myapp.shoppingmall.entities;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;



@Entity
@Table(name = "admin") //DB의 admin 테이블
@Data
public class Admin implements UserDetails {
	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 id자동생성
	private int id;
	
	private String username;
	
	private String password;
		
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 권한 목록을 리턴 (관리자 권한)
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료 되지 않았음? 
		return true; // 만료 안됨
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠겨 있지 않음?
		return true; // 잠기지 않음
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호가 만료되지 않음?
		return true; //만료안됨
	}

	@Override
	public boolean isEnabled() {
		// 사용가능한 계정?
		return true; //사용가능
	}

}
