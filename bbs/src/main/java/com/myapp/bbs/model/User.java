package com.myapp.bbs.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data   			// get set toString
@AllArgsConstructor // 모든 필드변수로 생성자 생성
public class User {
	private String email;
	private String password;
	private String name;
}
