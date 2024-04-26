package com.ezen.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.test.domain.CommentVO;
import com.ezen.test.repository.CommentDAO;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		log.info("CommentServiceImpl in");
		return cdao.post(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		log.info("getList in");
		return cdao.getList(bno);
	}

	@Override
	public int modify(CommentVO cvo) {
		log.info("modify in");
		return cdao.update(cvo);
	}

	@Override
	public int delete(int cno) {
		log.info("delete in");
		return cdao.delete(cno);
	}



}
