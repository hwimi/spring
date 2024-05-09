package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardService {

	int insert(BoardDTO bdto);

	List<BoardVO> getlist(PagingVO pgvo);

	BoardDTO getdetail(int bno);

//	int modify(BoardVO bvo);

	int delete(int bno);

	int getTotal(PagingVO pgvo);

	int uuid(String uuid);

	int update(BoardDTO boardDTO);

	
	

}
