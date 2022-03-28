package com.myapp.demo.domain;

public class User {
	private String name;
	private int age;
	private boolean employed;
	private String gender;

	public User() {
		// 빈 생성자와 전체필드가 들어간 생성자
	}

	public User(String name, int age, boolean employed, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.employed = employed;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isEmployed() {
		return employed;
	}

	public void setEmployed(boolean employed) {
		this.employed = employed;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", employed=" + employed + ", gender=" + gender + "]";
	}
	
}
