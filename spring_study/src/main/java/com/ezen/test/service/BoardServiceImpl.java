package com.ezen.test.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	

	@Autowired(required=true)
	private BoardDAO bdao;
	

	@Override
	public int insert(BoardVO bvo) {
		log.info("board registe service check");
		return bdao.insert(bvo);
	}


	@Override
	public List<BoardVO> getlist(PagingVO pgvo) {
		log.info("board getlist service check");
		return bdao.getlist(pgvo);
	}


	@Override
	public BoardVO getDetail(int bno) {
		log.info("getDetail  service check");
		bdao.updateReadCount(bno);
		return bdao.getDetail(bno);
	}


	@Override
	public void update(BoardVO bvo) {
		log.info("update  service check");
		bdao.update(bvo);
	}



	@Override
	public void remove(int bno) {
		bdao.remove(bno);
		
	}


	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return bdao.getTotal();
	}


	@Override
	public int getTotal(PagingVO pgvo) {
		log.info("update  service check");
		return bdao.getTotal(pgvo);
	}






}
