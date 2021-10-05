package com.health.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.health.domain.PostDTO;

@Mapper
public interface Admin_NoticeMapper {
	//공지 상세조회
	public PostDTO selectNoticeDetail(int postId);
	//작성
	public int insertNotice(PostDTO params);
	//수정
	public int updateNotice(PostDTO params);
	//삭제
	public int deleteNotice(int postId);
	//리스트
	public List<PostDTO> selectPostList(PostDTO params);
	//리스트 전처리
	public int selectPostTotalCount(PostDTO params);
	
}
