package com.health.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.domain.PostDTO;
import com.health.mapper.Admin_QuestionMapper;
import com.health.paging.PaginationInfo;

@Service
public class Admin_QuestionServiceImpl implements Admin_QuestionService  {
	
	@Autowired
	private Admin_QuestionMapper adminQuestMapper;
	
	@Override
	public boolean adminQuestionAnswer(PostDTO params) {
		int queryResult = 0;
		
		queryResult = adminQuestMapper.updatePost(params);
		
		return (queryResult == 1)? true : false;
	}

	@Override
	public PostDTO getPostDetail(int postId) {
		return adminQuestMapper.adminSelectPostDetail(postId);
	}

	@Override
	public List<PostDTO> getPostList(PostDTO params) {
		List<PostDTO> postList = Collections.emptyList();
		
		int postTotalCount = adminQuestMapper.selectPostTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(postTotalCount);
		
		params.setPaginationInfo(paginationInfo);
			
		if(postTotalCount > 0) {
			postList = adminQuestMapper.selectPostList(params);
		}
		return postList;
	
	}
}
