package com.health;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.domain.PostDTO;
import com.health.mapper.AdminQuestionMapper;
import com.health.paging.Criteria;

@SpringBootTest
class MapperTests {

		@Autowired
		private AdminQuestionMapper adminQuestionMapper;
		
		@Test
		public void testOfDetail() {
			PostDTO post = adminQuestionMapper.adminSelectPostDetail((int)1);
			try {
				String postJson = new ObjectMapper().writeValueAsString(post);
				
				System.out.println("===================");
				System.out.println(postJson);
				System.out.println("===================");
			} catch (JsonProcessingException e){
				e.printStackTrace();
			}
			
		}
		
		@Test
		public void testOfanswer() {
			PostDTO params = new PostDTO();
			params.setAnswer("답변이 추가되었습니다.");
			params.setModifier("관리자");
			params.setPostId((int)1);
			
			int result = adminQuestionMapper.answerPost(params);
			if(result == 1) {
				PostDTO post = adminQuestionMapper.adminSelectPostDetail((int)1);
				try {
					String postJson = new ObjectMapper().writeValueAsString(post);
					
					System.out.println("================");
					System.out.println(postJson);
					System.out.println("================");
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
		
		@Test
		public void testSelectList(PostDTO params) {
			int postTotalCount = adminQuestionMapper.selectPostTotalCount(params);
			if(postTotalCount > 0) {
				List<PostDTO> postList = adminQuestionMapper.selectPostList(params);
				if(CollectionUtils.isEmpty(postList)==false) {
					for(PostDTO post : postList) {
						System.out.println("==================");
						
						System.out.println(post.getTitle());
						System.out.println(post.getPostContents());
						System.out.println(post.getMbrId());
						System.out.println("==================");
					}
				}
			}
		}
		
		@Test
		public void testMakeQueryString() {
			PostDTO test = new PostDTO();
			System.out.println(test.makeQueryString(1));
		}
}
