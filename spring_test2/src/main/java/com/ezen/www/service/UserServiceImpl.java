package com.ezen.www.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.UserVO;
import com.ezen.www.repository.UserDAO;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	private final UserDAO udao;
	
	@Transactional
	@Override
	public int register(UserVO uvo) {
		//권한 추가
		int isOk=udao.insert(uvo);
		//
		return udao.insertAuthInit(uvo.getEmail());
	}

	@Override
	public int updateLastLogin(String authEmail) {
		// TODO Auto-generated method stub
		return udao.updateLastLogin(authEmail);
	}

	@Override
	public List<UserVO> getlist() {
		
		return udao.getlis();
	}

	@Override
	public int update(UserVO uvo) {
		
		return udao.update(uvo);
	}

	@Override
	public void remove(String id) {
		udao.removerauth(id);
		udao.remove(id);
		
	}



	

	




}
