package com.ezen.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PagingVO {
	private int pageNo;	//카페 1번
	private int qty;
	private String type;
	private String keyword;
	
	public PagingVO() {
		this.pageNo=1;
		this.qty=10;
	}
	
	public PagingVO(int pageNo,int qty) {
		this.pageNo=pageNo;
		this.qty=qty;
	}
	
	//db 에서 사용 될 시작 번지 구하기
	//select*from board limit 번지,개수 =>0부터 시작
	//page1 limit 0,10 =>page2 바뀌게 되면 limit 10 10
	
	public int getPageStart() {
		return (this.pageNo-1)*this.qty;
	}
	//type 의 복합타입을 배열로 생성
	//복학 타입의 키워드 일 경우 각자 검색하게 하기 위해 배열로 생성
	public String[] getTypetoArray() {
		return this.type==null? new String[] {}:this.type.split("");
	}
}
