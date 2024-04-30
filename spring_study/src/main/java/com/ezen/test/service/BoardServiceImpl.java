package com.ezen.test.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.test.domain.BoardDTO;
import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.FileVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.repository.BoardDAO;
import com.ezen.test.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	

	@Autowired(required=true)
	private BoardDAO bdao;
	
	@Autowired(required = true)
	private FileDAO fado;



	@Override
	public List<BoardVO> getlist(PagingVO pgvo) {
		log.info("board getlist service check");
		return bdao.getlist(pgvo);
	}

	@Override
	public int insert(BoardDTO bdto) {
		log.info(">>board register service cherck");
		int isOk=bdao.insert(bdto.getBvo());
		
		if(bdto.getFlist()==null) {
			return isOk;
		}else {
			//파일저장
			if(isOk>0&&bdto.getFlist().size()>0) {
				//bno 아직없음
				//insert 를 통해서 자동생성=>db에서 가져오기
				int bno=bdao.selectBno();
				for(FileVO fvo:bdto.getFlist()) {
					fvo.setBno(bno);
					//파일저장
					isOk*=fado.insertFile(fvo);
				}
			}
		}
		
		return isOk;
	}

	@Override
	public BoardDTO getDetail(int bno) {
		log.info("getDetail  service check");
		bdao.updateReadCount(bno);
		BoardDTO bdto=new BoardDTO();
		BoardVO bvo=bdao.getDetail(bno);
		bdto.setBvo(bvo);
		bdto.setFlist(fado.getFileList(bno));
		return bdto;
	}


	@Override
	public void update(BoardDTO bdto) {
		log.info("update update service check");
		//파일추가 작업
		//bvo=>boardMapper /flist=>fileMapper
		
		int isOk=bdao.update(bdto.getBvo());
		//파일값이 없다면..
		if(bdto.getFlist()==null) {
			return;
		}
		//bvo 업데이트 완료후 파일도 있다면.. 파일추가
		if(isOk>0 && bdto.getFlist().size()>0) {
			//bno setting
			for(FileVO fvo:bdto.getFlist()) {
				fvo.setBno(bdto.getBvo().getBno());
				isOk*=fado.insertFile(fvo);
			}
		}
		
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

	@Override
	public int removeFile(String uuid) {
		// TODO Auto-generated method stub
		return fado.removeFile(uuid);
	}

	@Override
	public void cmtFileUpdate() {
		bdao.cmtFileCountUpdate();
		bdao.fileCountUdate();
		
	}

	




	






}
