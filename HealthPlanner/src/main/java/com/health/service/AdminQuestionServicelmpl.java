package com.health.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.domain.PostDTO;
import com.health.mapper.AdminQuestionMapper;
import com.health.paging.Criteria;

@Service
public class AdminQuestionServicelmpl implements AdminQuestionService  {
	
	@Autowired
	private AdminQuestionMapper adminQuestMapper;
	
	@Override
	public boolean adminQuestionAnswer(PostDTO params) {
		int queryResult = 0;
		
		if(params.getAnswerState() == 0) 
			queryResult = adminQuestMapper.answerPost(params);
		
		return (queryResult == 1)? true : false;
	}

	@Override
	public PostDTO getPostDetail(int postId) {
		return adminQuestMapper.adminSelectPostDetail(postId);
	}

	@Override
	public List<PostDTO> getPostList(Criteria criteria) {
		List<PostDTO> postList = Collections.emptyList();
		
		int postTotaclCount = adminQuestMapper.selectPostTotalCount(criteria);
		
		if(postTotaclCount > 0) {
			postList = adminQuestMapper.selectPostList(criteria);
		}
		
		return postList;
	}

}
