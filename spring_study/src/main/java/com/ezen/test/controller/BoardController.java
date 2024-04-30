package com.ezen.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.test.domain.BoardDTO;
import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.FileVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.handler.FileHandler;
import com.ezen.test.handler.PagingHandler;
import com.ezen.test.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {

	@Autowired(required = true)
	private BoardService bsv;
	@Autowired
	private FileHandler fhd;

	@GetMapping("/register") // getmapping 조회할때 사용한다
	public String register() {
		return "/board/register";
	}

	// RequestParm("name") String name :파라미터를 받을때
	//required :필수여부 false:파라미터가 없어도 예외가 발생하지않음
	@PostMapping("/insert") // postmapping저장할때 사용
	public String insert(BoardVO bvo,@RequestParam(name="files",required = false)MultipartFile[] files) {
		log.info(">>>>bvo>>>>{}", bvo);
		log.info(">>>>files>>>>{}", files);
		//파일 핸들러 처리
		//파일을 저장처리=>filelist 리턴
		List<FileVO> flist=null;
		//파일이 있을 경우에만 핸들러 호출
		if(files[0].getSize()>0) {
			//핸들러
			flist=fhd.uploadFiles(files);
			log.info("flist{}",flist);
			
		}
		BoardDTO bdto=new BoardDTO(bvo,flist);
		int isOk = bsv.insert(bdto);
		return "redirect:/board/list";

	}

	@GetMapping("/list")
	public String list(Model m,PagingVO pgvo /*board bvo 수정부분*/) { //PagingVO 파라미터가 없으면 null값이 된다
		log.info(">>>pgvo>>{}",pgvo);
		// 리턴타입은 목적지 경로에 대한 타입 (destpage가 관련)
		// model 객체 =>request.setAttribut
		
		//cmt_qty has_file update 후 리스트 가져오기
		bsv.cmtFileUpdate();
		
		
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
		log.info(">>>>detail>>>>{}", bno);
		BoardDTO bdto=bsv.getDetail(bno);
		log.info(">>bdto>>{}",bdto);
		m.addAttribute("bdto", bdto);
		//m.add 이름 설정해서 가져가는거 js 로
	}
	@PostMapping("modify")
	public String modify(BoardVO bvo,@RequestParam(name="files",required = false)MultipartFile[] files) {
		log.info(">>>>modify>>>>{}", bvo);
		List<FileVO> flist=null;
		
		//fileHandler multipartFile[]=>flist
		if(files[0].getSize()>0) {
			flist=fhd.uploadFiles(files);
		}
		BoardDTO bdto=new BoardDTO(bvo,flist);
		bsv.update(bdto);
		//board/detail.jsp:새로운 데이터를 가지고 param 이 필요함
		return"redirect:/board/detail?bno="+bvo.getBno();
	}
	
	@GetMapping("remove")
	public String remove(@RequestParam("bno") int bno) {
		log.info(">>>>remove>>>>{}", bno);
		bsv.remove(bno);
		return "redirect:/board/list";
	}
	@DeleteMapping(value="/{uuid}",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid) {
		log.info(">>uuid>{}",uuid);
		int isOk=bsv.removeFile(uuid);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK):
			new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
