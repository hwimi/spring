package com.ezen.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.handler.PagingHandler;
import com.ezen.test.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {

	@Autowired(required = true)
	private BoardService bsv;

	@GetMapping("/register") // getmapping 조회할때 사용한다
	public String register() {
		return "/board/register";
	}

	// RequestParm("name") String name :파라미터를 받을때
	@PostMapping("/insert") // postmapping저장할때 사용
	public String insert(BoardVO bvo) {
		log.info(">>>>bvo>>>>{}", bvo);
		int isOk = bsv.insert(bvo);
		return "redirect:/board/list";

	}

	@GetMapping("/list")
	public String list(Model m,PagingVO pgvo) { //PagingVO 파라미터가 없으면 null값이 된다
		log.info(">>>pgvo>>{}",pgvo);
		// 리턴타입은 목적지 경로에 대한 타입 (destpage가 관련)
		// model 객체 =>request.setAttribut
		List<BoardVO> list = bsv.getlist(pgvo);
		int totalCount=bsv.getTotal(pgvo);
		PagingHandler ph=new PagingHandler(pgvo, totalCount);
		log.info(">>>ph>>{}",ph);
		m.addAttribute("list", list);
		m.addAttribute("ph",ph);
		return "/board/list";
	}
	//detail =>board/detail =>return /board/detail
	//modify =>board/modify =>return /board/modify
	//controller 로 들어오는 경로와 jsp 로 나가는 경로가 일치하면 void 처리할수 있움.
	@GetMapping({"detail","/modify"})
	public void detail(Model m,@RequestParam("bno")int bno) {
		BoardVO bvo=bsv.getDetail(bno);
		log.info(">>>>detail>>>>{}", bvo);
		m.addAttribute("bvo", bvo);
		
	}
	@PostMapping("modify")
	public String modify(BoardVO bvo) {
		log.info(">>>>modify>>>>{}", bvo);
		bsv.update(bvo);
		//board/detail.jsp:새로운 데이터를 가지고 param 이 필요함
		return"redirect:/board/detail?bno="+bvo.getBno();
	}
	
	@GetMapping("remove")
	public String remove(@RequestParam("bno") int bno) {
		log.info(">>>>remove>>>>{}", bno);
		bsv.remove(bno);
		return "redirect:/board/list";
	}
	
	

}
