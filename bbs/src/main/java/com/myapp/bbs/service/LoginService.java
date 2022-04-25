package com.myapp.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.bbs.dao.UserMapper;
import com.myapp.bbs.model.Login;
import com.myapp.bbs.model.User;

@Service
public class LoginService {

	@Autowired
	private UserMapper userMapper;
	
	/*  인증하기 메소드 : 실패할경우 login 객체에 에러메세지 입력함 */
	public void authenticate(Login login) {
		//이메일로 검색해서 유저 찾기
		User user = userMapper.selectByEmail(login.getEmail());
		
		if(user == null) {
			login.setError("이메일이 존재하지 않습니다.");
		} else {
			if (!user.getPassword().equals(login.getPassword())) {
				login.setError("패스워드가 틀립니다.");
			} else {
				login.setError(null); //에러 없음 ( 인증됨 )
			}
			
		}
	}
	
	/*  유저 찾기 메소드 : 이메일로 유저찾기 */
	public User findUserByEmail(String email) {
		User user = userMapper.selectByEmail(email);
		return user;
	}
}
