package com.ezen.www.controller;


import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.UserVO;
import com.ezen.www.service.UserService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/user/*")
@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
	private final UserService usv;
	private final BCryptPasswordEncoder bcEncoder;
	
	//controller mapping 과 jsp 경로가 같으면 void 가능
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(UserVO uvo) {
		log.info(">>uvo>>{}",uvo);
		uvo.setPwd(bcEncoder.encode(uvo.getPwd()));
		int isOk=usv.register(uvo);
		return"index";
	}
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		
		//로그인 실패시 다시 로그인 페이지로 돌아와 오류 메시지로 전송
		//다시 로그인 유도
		log.info(">>errMsg>>{}",request.getAttribute("errMsg").toString());
		re.addAttribute("email",request.getAttribute("email"));
		re.addAttribute("errMsg",request.getAttribute("errMsg"));
		
		return"redirect:/user/login";
	}
	@GetMapping("/list")
	public String list(Model m,UserVO uvo) {
		List<UserVO>list=usv.getlist();
		m.addAttribute("list",list);
		return "/user/list";
		

	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request, response, authentication);
	}
	
	@GetMapping("/modify")
	public void modify() {}
	
	@PostMapping("/modify")
	public String modfiy (UserVO uvo,HttpServletRequest request, HttpServletResponse response) {
		if(uvo.getPwd()!=null) {
			uvo.setPwd(bcEncoder.encode(uvo.getPwd()));
		}
		
		int isOk=usv.update(uvo);
		logout(request, response);
		return "redirect:/";
	}
	@GetMapping("/remove")
	public String remove(Principal principal,HttpServletRequest request,HttpServletResponse response) {
		String id=principal.getName(); //id email
		usv.remove(id);
		logout(request, response);
		return "redirect:/";
	}
	
}

