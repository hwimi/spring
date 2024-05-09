package com.ezen.www.repository;

import java.security.Principal;
import java.util.List;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.UserVO;

public interface UserDAO {

	int insert(UserVO uvo);

	int insertAuthInit(String email);

	UserVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	List<UserVO> getlis();

	int update(UserVO uvo);

	void remove(String id);

	void removerauth(String id);

	

	

	

}
