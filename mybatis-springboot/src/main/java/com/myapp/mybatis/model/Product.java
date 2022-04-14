package com.myapp.mybatis.model;

public class Product {
	
	private Long prodId;  //CamelCas 중간단어는 대문자로 시작
	private String prodName; //prod_id , prod_name, prod_id DB 열이름
	private int prodPrice;
	
	//모든 필드변수
	public Product(Long prodId, String prodName, int prodPrice) {
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}
	//id빼고 (입력시에 id는 자동생성되므로)
	public Product(String prodName, int prodPrice) {
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}
	
	public Long getProdId() {
		return prodId;
	}
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	
	
}
