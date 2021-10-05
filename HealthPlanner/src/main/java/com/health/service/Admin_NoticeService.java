package com.health.service;

import java.util.List;

import com.health.domain.PostDTO;

public interface Admin_NoticeService {
	
	//상세
	public PostDTO getNoticeDetail(int postId);
	//작성 & 수정
	public boolean writeNotice(PostDTO params);
	//삭제
	public boolean deleteNotice(int postId);
	//리스트
	public List<PostDTO> getNoticeList(PostDTO params);
	
}
