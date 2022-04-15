package com.myapp.bbs.dao;

import org.apache.ibatis.annotations.Mapper;

import com.myapp.bbs.model.BoardVO;

@Mapper
public interface BoardMapper {
	
	/* 게시판 등록 */
	public void enroll(BoardVO board);

}
