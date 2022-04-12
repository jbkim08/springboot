package com.myapp.shoppingmall.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myapp.shoppingmall.dao.AdminRepository;
import com.myapp.shoppingmall.dao.UserRepository;
import com.myapp.shoppingmall.entities.Admin;
import com.myapp.shoppingmall.entities.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 유저DB에서 필요한 유저또는 관리자정보를 읽어온다.(입력파라메터는 username)
		User user = userRepo.findByUsername(username);
		Admin admin = adminRepo.findByUsername(username);
		
		if(admin != null) return admin; //먼저 관리자 있으면 관리자로 리턴
		if(user != null) return user;	//유저가 있으면 유저로 리턴
		
		throw new UsernameNotFoundException("유저 " + username + "이 없습니다");
	}

}
