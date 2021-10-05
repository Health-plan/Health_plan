package com.health.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.health.domain.PostDTO;

@Mapper
public interface Admin_QuestionMapper {
	//문의글 상세조회
	public PostDTO adminSelectPostDetail(int postId);
	//답변
	public int answerPost(PostDTO params);
	//게시글 리스트
	public List<PostDTO> selectPostList(PostDTO params);
	//페이징에 사용할 카운트
	public int selectPostTotalCount(PostDTO params);
	
}
