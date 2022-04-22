package com.myapp.bbs.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * DB의 reply 테이블에서 데이터를 받을 객체
 * @author admin
 *
 */
@Getter
@Setter
@ToString
public class ReplyVO {
	
	/* 댓글 번호 */
	private int reply_no;
	
	/* 게시글 번호 */
	private int reply_bno;
	
	/* 댓글 내용 */
	private String content;
	
	/* 댓글 작성자 */
	private String writer;
	
	/* 댓글 등록 날짜 : AJAX JSON으로 보내기 포맷 */
	@JsonFormat(pattern = "yyyy-MM-dd a hh:mm:ss") 
	private LocalDateTime created_at; 

	/* 댓글 업데이트 날짜 : AJAX JSON으로 보내기 포맷 */
	@JsonFormat(pattern = "yyyy-MM-dd a hh:mm:ss") 
	private LocalDateTime updated_at;


	/* 전체 생성자 */
	public ReplyVO(int reply_no, int reply_bno, String content, String writer, LocalDateTime created_at,
			LocalDateTime updated_at) {
		this.reply_no = reply_no;
		this.reply_bno = reply_bno;
		this.content = content;
		this.writer = writer;
		this.created_at = created_at;
		this.updated_at = updated_at;
	} 

}
