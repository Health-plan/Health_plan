package com.health.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.domain.PostDTO;
import com.health.mapper.Admin_NoticeMapper;
import com.health.paging.PaginationInfo;

@Service
public class Admin_NoticeServiceImpl implements Admin_NoticeService{
	
	@Autowired
	private Admin_NoticeMapper adminNoticeMapper;
	
	//상세
	@Override
	public PostDTO getNoticeDetail(int postId) {
		return adminNoticeMapper.selectNoticeDetail(postId); 
	};
	
	//작성 & 수정
	@Override
	public boolean writeNotice(PostDTO params) {
		int queryResult = 0;
		
		if(params.getPostId() == 0) {
			queryResult = adminNoticeMapper.insertNotice(params);
		} else {
			queryResult = adminNoticeMapper.updateNotice(params);
		}
		
		return (queryResult == 1) ? true : false;
	};
	
	//삭제
	@Override
	public boolean deleteNotice(int postId) {
		int queryResult = 0;
		
		PostDTO post = adminNoticeMapper.selectNoticeDetail(postId);
		
		if(post != null && post.getAvailable() != 0) {
			queryResult = adminNoticeMapper.deleteNotice(postId);
		}
	
		return (queryResult == 1) ? true : false;
	};
	
	//리스트
	@Override
	public List<PostDTO> getNoticeList(PostDTO params){
		//비어있는 list도 return하기 위해 사용
		List<PostDTO> postList = Collections.emptyList();
		
		int noticeTotalCount = adminNoticeMapper.selectPostTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(noticeTotalCount);
		
		params.setPaginationInfo(paginationInfo);
		
		if(noticeTotalCount > 0) {
			postList = adminNoticeMapper.selectPostList(params);
		}
		
		return postList;
	};
	
}
