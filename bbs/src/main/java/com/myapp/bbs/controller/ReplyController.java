package com.myapp.bbs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.bbs.model.ReplyVO;
import com.myapp.bbs.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	//ReplyService 객체를 생성자 주입
	private ReplyService replyService;
	//생성자 객체 주입시에는 @Autowired 필요없음
	public ReplyController(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@PostMapping
	public ReplyVO replyEnrollPOST(@RequestBody ReplyVO reply) {
		//입력된 json타입 데이터를 받아서 reply객체 리턴(제이슨타입)
		replyService.enroll(reply); //DB저장
		return reply; //제이슨타입
	}
	
	@GetMapping("/{bno}") //게시글에 달린 댓글들을 불러오기
	public List<ReplyVO> replyListGET(@PathVariable int bno){
		return replyService.getReplyList(bno);
	}
	
	@PutMapping
	public ReplyVO replyUpdatePUT(@RequestBody ReplyVO reply) {
		replyService.modify(reply); //DB에 댓글 데이터 수정하기
		return reply;				//테스트로 reply 리턴
	}
}











