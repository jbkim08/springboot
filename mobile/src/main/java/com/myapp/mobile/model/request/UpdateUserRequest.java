package com.myapp.mobile.model.request;

import javax.validation.constraints.Size;

public class UpdateUserRequest {
	//유저 객체에서 이메일 비번 제외한 이름name만 업데이트 함
	@Size(min = 2, message = "이름 길이는 2 이상")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
