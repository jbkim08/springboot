package com.myapp.bbs.model;

/**
 * 페이지 계산을 위한 클래스
 * @author admin
 *
 */
public class Criteria {
	
    /* 현재 페이지 */
    private int pageNum;
    
    /* 한 페이지 당 보여질 게시물 갯수 */
    private int amount;
    
	/* 스킵 할 게시물 수( (pageNum-1) * amount ) */
	private int skip;
	
	/* 기본 생성자 -> 기봅 세팅 : pageNum = 1, amount = 10 */
	public Criteria() {
		this(1,10); //전체 생성자를 통해 (1,10)을 입력해 객체 생성
	}	
    
    /* 생성자 => 원하는 pageNum, 원하는 amount */
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum-1) * amount;
	}

	public int getPageNum() {
		return pageNum;
	}
	
	//새로 페이지숫자를 설정 했을때 스킵도 계산
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		this.skip = (pageNum-1) * amount;
	}

	public int getAmount() {
		return amount;
	}

	//페이지당 갯수를 바꿀경우에도 스킵을 다시 계산
	public void setAmount(int amount) {
		this.amount = amount;
		this.skip = (pageNum-1) * amount;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + ", skip=" + skip + "]";
	}
	
    
}
