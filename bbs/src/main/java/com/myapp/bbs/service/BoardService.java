package com.myapp.bbs.service;

import java.util.List;

import com.myapp.bbs.model.BoardVO;

public interface BoardService {
	
    /* 게시판 등록 */
    public void enroll(BoardVO board);
    
	/* 게시판 목록 */
	public List<BoardVO> getList();
	
	 /* 게시판 조회 */
    public BoardVO getPage(int bno);
    
    /* 게시판 수정 */
    public int modify(BoardVO board);
    
    /* 게시판 삭제 */
    public int delete(int bno);
 
}
