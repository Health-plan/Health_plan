package com.health.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.domain.PostDTO;
import com.health.mapper.Admin_QnAMapper;
import com.health.paging.PaginationInfo;

@Service
public class Admin_QnAServiceImpl implements Admin_QnAService{
	
	@Autowired
	private Admin_QnAMapper adminQnAMapper;
	
	//상세
	@Override
	public PostDTO getQnADetail(int postId) {
		return adminQnAMapper.selectQnADetail(postId); 
	};
	
	//작성 & 수정
	@Override
	public boolean writeQnA(PostDTO params) {
		int queryResult = 0;
		
		if(params.getPostId() == 0) {
			queryResult = adminQnAMapper.insertQnA(params);
		} else {
			queryResult = adminQnAMapper.updateQnA(params);
		}
		
		return (queryResult == 1) ? true : false;
	};
	
	//삭제
	@Override
	public boolean deleteQnA(int postId) {
		int queryResult = 0;
		
		PostDTO post = adminQnAMapper.selectQnADetail(postId);
		System.out.println(post);
		
		//if조건문에서 0 비교할 때, int자료형의 경우 0을 쓰면 null과 같이 인식되므로 '0'을 써아햠
		if(post != null && post.getAvailable() != '0') {
			queryResult = adminQnAMapper.deleteQnA(postId);
			System.out.println("삭제메소드 값 : "+queryResult);
		}
	
		return (queryResult == 1) ? true : false;
	};
	
	//리스트
	@Override
	public List<PostDTO> getQnAList(PostDTO params){
		//비어있는 list도 return하기 위해 사용
		List<PostDTO> postList = Collections.emptyList();
		
		int QnATotalCount = adminQnAMapper.selectPostTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(QnATotalCount);
		
		params.setPaginationInfo(paginationInfo);
		
		if(QnATotalCount > 0) {
			postList = adminQnAMapper.selectPostList(params);
		}
		
		return postList;
	};
	
}
