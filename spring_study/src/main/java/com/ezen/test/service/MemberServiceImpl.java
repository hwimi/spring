package com.ezen.test.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.test.domain.MemberVO;
import com.ezen.test.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired(required=true)
	private MemberDAO mdao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	HttpServletRequest request;

	@Override
	public int insert(MemberVO mvo) {
		//아이디가 중복되는경우 회원가입 실패
		//아이디만 주고 DB에서 일치하는 MVO 객체를 리턴=>일치하는 객체가 있으면 가입실패/없으면 가능
		MemberVO tempmvo=mdao.getUser(mvo.getId());
		if(tempmvo!=null) {
			//기존 아이디가 있는경우
			return 0;
		}
		//아이디가 중복되지 않는다면 회원가입을 진행
		//password가 null 이거나 값이 없다면 가입불가
		if(mvo.getId()==null||mvo.getId().length()==0) {
			return 0;
		}
		if(mvo.getPw()==null||mvo.getPw().length()==0) {
			return 0;
		}
		
		//회원가입 진행
		//password 암호화하여 가입
		//encode():암호화 /matches(입련된비번,암호화된 비번)=>true/fasle
		
//		String pw=mvo.getPw();
//		String encodePW=passwordEncoder.encode(pw);
//		mvo.setPw(encodePW);
		
		mvo.setPw(passwordEncoder.encode(mvo.getPw()));
		int isOk=mdao.insert(mvo);
		log.info("insert serviec in");
		return isOk;
	}

	@Override
	public MemberVO isUser(MemberVO mvo) {
		//로그인 유저 확인
		MemberVO tempMvo=mdao.getUser(mvo.getId()); //회원가입 했을 때 썻던 메서드 활용
		
		//해당 아이디가 없으면
		if(tempMvo==null) {
			return null;
		}
		//matches (원래비번 암호화된 비번) 비교
		if(passwordEncoder.matches(mvo.getPw(), tempMvo.getPw())) {
			return tempMvo;
		}
		return null;
	}

	@Override
	public void lastloginUpdate(String id) {
		mdao.lastloginUpdate(id);
		
	}

	@Override
	public void modify(MemberVO mvo) {
		//pw 여부에 따라 변경사항을 나누어서 처리
		//pw가 없다ㅏ면 기존값 설저/ 있다면 암화처리하여 수정
		if(mvo.getPw()==null ||mvo.getPw().length()==0) {
			MemberVO sesMvo=(MemberVO)request.getSession().getAttribute("ses");
			mvo.setPw(sesMvo.getPw());
			
		}else {
			String SetPw=passwordEncoder.encode(mvo.getPw());
			mvo.setPw(SetPw);
		}
		log.info(">>pw 수정후>>{}",mvo);
		mdao.updae(mvo);
	}

	@Override
	public void remove(String id) {
		log.info("remove service in>>{}",id);
		mdao.remove(id);
		
	}



}
