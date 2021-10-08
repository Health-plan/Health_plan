package com.health.service;

import java.util.List;

import com.health.domain.PostDTO;

public interface Admin_QnAService {
	
	//상세
	public PostDTO getQnADetail(int postId);
	//작성 & 수정
	public boolean writeQnA(PostDTO params);
	//삭제
	public boolean deleteQnA(int postId);
	//리스트
	public List<PostDTO> getQnAList(PostDTO params);
	
}
