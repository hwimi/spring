package com.ezen.www.service;

import java.security.Principal;
import java.util.List;

import com.ezen.www.domain.UserVO;

public interface UserService {

	int register(UserVO uvo);

	int updateLastLogin(String authEmail);

	List<UserVO> getlist();

	int update(UserVO uvo);

	void remove(String id);

	

	

	
	
}
