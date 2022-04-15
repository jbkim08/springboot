package com.myapp.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.bbs.model.BoardVO;
import com.myapp.bbs.service.BoardService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/board")
@Log		//콘솔의 로그 출력 (print out 대신 사용)
public class BoardController {
	
	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/list")
	public String boardListGet() {
		log.info("게시판 리스트 페이지 진입");
		return "list";
	}
	
	@GetMapping("/enroll")
	public String boardEnrollGet(Model model) {
		model.addAttribute("board", new BoardVO());
		return "enroll";
	}
	
	@PostMapping("/enroll")
	public String boardEnrollPost(BoardVO board, RedirectAttributes attr) {
		//log.info("BoardVO : " + board);
		boardService.enroll(board);
		attr.addFlashAttribute("message", "게시물 등록 성공!");
		return "redirect:/board/list"; //포스트 다음에 리다이렉트
	}
}





