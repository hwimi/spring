package com.ezen.test.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.service.BoardService;

@Mapper
public interface BoardDAO {

	

	int insert(BoardVO bvo);

	List<BoardVO> getlist(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int update(BoardVO bvo);

	void remove(int bno);

	void updateReadCount(int bno);

	int getTotal();

	int getTotal(PagingVO pgvo);

	int selectBno();

	void cmtFileCountUpdate();

	void fileCountUdate();

	

	

}
