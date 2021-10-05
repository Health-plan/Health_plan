package com.health.service;

import java.util.List;

import com.health.domain.PostDTO;

public interface Admin_QuestionService {
	
	public boolean adminQuestionAnswer(PostDTO params);
	
	public PostDTO getPostDetail(int postId);
	
	public List<PostDTO> getPostList(PostDTO params);

}
