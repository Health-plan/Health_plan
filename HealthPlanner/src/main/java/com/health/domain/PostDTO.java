package com.health.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class PostDTO extends CommonDTO {
	//게시글 식별자
	private	int	postId;
	//종류(문의/공지/자주질문)
	private	int	boardType;
	//분류(세부)
	private	int boardSubType;
	//제목
	private	String title;
	//내용
	private	String postContents;
	//답변내용
	private String answer;
	//답변여부
	private	int	answerState;
	//삭제여부
	private	int	available;
	//조회수
	private	int	views;
	//작성자(members mbsId(pk))
	private	String mbrId;
	//작성일시(일단 string으로)
	private	String postDate;
	//수정자
	private	String modifier;
	//수정일시(일단 string으로)
	private	String modifyDate;
	
	
	@Override
	public String toString() {
		return "PostDTO [postId=" + postId + ", boardType=" + boardType + ", boardSubType=" + boardSubType + ", title="
				+ title + ", postContents=" + postContents + ", answer=" + answer + ", answerState=" + answerState
				+ ", available=" + available + ", views=" + views + ", mbrId=" + mbrId + ", postDate=" + postDate
				+ ", modifier=" + modifier + ", modifyDate=" + modifyDate + ",\n currentPageNo=" + super.getCurrentPageNo() + ", pageSize=" + super.getPageSize() + ", sortType = " + super.getSortType()  +  "]" ;
	}
	
	

	
	
}
