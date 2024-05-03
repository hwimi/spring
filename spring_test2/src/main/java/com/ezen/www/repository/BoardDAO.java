package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardDAO {

	

	int insert(BoardVO bvo);

	List<BoardVO> getlist(PagingVO pgvo);

	BoardVO getdetail(int bno);

	int update(BoardVO bvo);

	int delete(int bno);

	int getTotal(PagingVO pgvo);

	int selectOneBno();

	
	
}
