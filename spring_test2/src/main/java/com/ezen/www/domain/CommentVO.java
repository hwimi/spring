package com.ezen.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO {
	private int cno;
	private int bno;
	private String writer;
	private String content;
	private String regDate;
}
