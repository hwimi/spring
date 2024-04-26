package com.ezen.test.service;

import java.util.List;

import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.handler.PagingHandler;

public interface BoardService {

	int insert(BoardVO bvo);

	List<BoardVO> getlist(PagingVO pgvo);

	BoardVO getDetail(int bno);

	void update(BoardVO bvo);

	void remove(int bno);

	int getTotal();

	int getTotal(PagingVO pgvo);

	

}
