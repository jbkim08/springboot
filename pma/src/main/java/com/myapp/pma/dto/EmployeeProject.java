package com.myapp.pma.dto;

public interface EmployeeProject {
	//스프링이 자동 생성할수 있게 get메소드만 적어준다. 
    //set은 필요없음 DB에서 쿼리 결과를 가져오기만 하면 됨
	public String getLastName();  //lastName
	public String getFirstName(); //firstName
	public String getCount();	  //count

}
