package com.myapp.bbs.service;

import java.util.List;

import com.myapp.bbs.model.ReplyVO;

public interface ReplyService {
	
	/* 댓글 등록 */
	public void enroll(ReplyVO reply);
	
	/* 댓글 목록 (보드글 번호가 필요) */
	public List<ReplyVO> getReplyList(int reply_bno);
	
	/* 댓글 수정 */
	public int modify(ReplyVO reply);
	
	/* 댓글 삭제 */
	public int delete(int reply_no);	
}
