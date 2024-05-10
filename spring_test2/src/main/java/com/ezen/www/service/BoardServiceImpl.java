package com.ezen.www.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;

import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	
	private final BoardDAO bdao;
	private final FileDAO fdao;
	
	
	@Transactional
	@Override
	public int insert(BoardDTO bdto) {
		//bvo 저장후 bno set 한 후 filevo 저장
		int isOk=bdao.insert(bdto.getBvo());
		if(bdto.getFlist()==null) {
			return isOk;
		}
		if(isOk>0 && bdto.getFlist().size()>0) {
			//bno seettion
			int bno=bdao.selectOneBno(); //가장 마지막에 등록된 bno
			for(FileVO fvo:bdto.getFlist()) {
				fvo.setBno(bno);
				isOk=fdao.insertFile(fvo);
			}
			
		
		
		}
		return isOk;
	}

	@Override
	public List<BoardVO> getlist(PagingVO pgvo) {
		log.info("getlist in");
		return bdao.getlist(pgvo);
	}

	@Override
	public BoardDTO getdetail(int bno) {
		BoardVO bvo=bdao.getdetail(bno);
		bdao.updatereadcount(bno);
		List<FileVO> flist=fdao.getList(bno);
		BoardDTO bdto=new BoardDTO(bvo,flist);
		return bdto;
	}

	/*
	 * @Override public int modify(BoardVO bvo) { // TODO Auto-generated method stub
	 * return bdao.update(bvo); }
	 */

	@Override
	public int delete(int bno) {
		// TODO Auto-generated method stub
		return bdao.delete(bno);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getTotal(pgvo);
	}

	@Override
	public int uuid(String uuid) {
		// TODO Auto-generated method stub
		return fdao.uuid(uuid);
	}

	@Override
	public int update(BoardDTO boardDTO) {
		int isOk=bdao.update(boardDTO.getBvo());
		if(boardDTO.getFlist()==null) {
			return isOk;
		}
		if(isOk>0&&boardDTO.getFlist().size()>0) {
			for(FileVO fvo:boardDTO.getFlist()) {
				fvo.setBno(boardDTO.getBvo().getBno());
				isOk=fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public void cmtFileUpdate() {
		bdao.cmtFileCountUpdate();
		bdao.fileCountUdate();
		
	}



	
	}

	
	

