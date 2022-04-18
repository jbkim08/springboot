package com.myapp.bbs.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;

import com.myapp.bbs.model.BoardVO;

import lombok.extern.java.Log;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE) //실제 DB (연결되어 있는)로 테스트
@Rollback(value = false)  						   //테스트할때 롤백 하지 않음
@Log
public class BoardMapperTest {
	//JUNIT 5버젼 테스트
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void test() {
//		BoardVO vo = new BoardVO();
//		
//		vo.setTitle("제목테스트");
//		vo.setContent("내용테스트");
//		vo.setWriter("글쓴이");
//		
//		boardMapper.enroll(vo);
		
//		List<BoardVO> list = boardMapper.getList();
		/* for 반복문 */
//		 for(int i=0; i < list.size(); i++) {
//			 log.info("" + list.get(i));
//		 }
		/* 향상된 foreach문 */
//		 for(BoardVO vo : list) {
//			 log.info("" + vo);
//		 }
		/* foreach 메소드와 람다식 */
//		list.forEach(board -> log.info(""+board));
		
		/* 게시판 조회 */
//		int bno = 1;
//		log.info(""+boardMapper.getPage(bno));
		
		/* 게시판 수정 */
//		BoardVO board = new BoardVO();
//		board.setBno(1);
//		board.setTitle("수정된 제목");
//		board.setContent("수정된 게시글 내용");
//		
//		int result = boardMapper.modify(board);
//		
//		log.info("결과: "+result);
		
		/* 게시판 삭제 */
		int result = boardMapper.delete(9);
		log.info("결과 : " +result);
		
	}
}








