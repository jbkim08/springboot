package com.myapp.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myapp.bbs.dao.BoardMapper;
import com.myapp.bbs.model.BoardVO;
import com.myapp.bbs.model.Criteria;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;
	
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Override
	public void enroll(BoardVO board) {
		boardMapper.enroll(board);	
	}

	@Override
	public List<BoardVO> getList() {		
		return boardMapper.getList();
	}

	@Override
	public BoardVO getPage(int bno) {
		return boardMapper.getPage(bno);
	}

	@Override
	public int modify(BoardVO board) {
		return boardMapper.modify(board);
	}

	@Override
	public int delete(int bno) {
		return boardMapper.delete(bno);
	}

	@Override
	public List<BoardVO> getListPaging(Criteria cri) {
		return boardMapper.getListPaging(cri);
	}

	@Override
	public int getTotal() {
		return boardMapper.getTotal();
	}

}
