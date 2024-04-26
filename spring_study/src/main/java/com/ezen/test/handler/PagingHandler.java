package com.ezen.test.handler;

import com.ezen.test.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingHandler {
	//list 화면 
	
	private int startPage;
	private int endPage;
	private boolean prev,next;
	private int realEndPage;
	
	private int totalCount;
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo=pgvo;
		this.totalCount=totalCount;
		
		//1~10 11~20 21~30
		//pageNo =>1/10=>0.1(올림)=>1*10=>10
		//pageNo =>2/10=>0.2(올림)=>2*10=>10
		//pageNo =>9/10=>0.9(올림)=>9*10=>10
		//pageNo =>11/10=>1.1(올림)=>2*10=>20
		//정수/정수=정수
		
		
		this.endPage=(int)Math.ceil(pgvo.getPageNo()/(double)10)*10;
		this.startPage=endPage-9;
		
		//진짜 끝페이지
		//103=3 103/10=>10.3
		this.realEndPage=(int)Math.ceil(totalCount/(double)pgvo.getQty());
		if(realEndPage<endPage) {
			this.endPage=realEndPage;
		}
		this.prev=this.startPage>1;
		this.next=this.endPage<realEndPage;
		
	}
	

}
