package com.health;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.domain.LevelupTestDTO;
import com.health.domain.PostDTO;
import com.health.mapper.Admin_LevelupTestMapper;
import com.health.mapper.Admin_QuestionMapper;

@SpringBootTest
class MapperTests {

		@Autowired
		private Admin_QuestionMapper adminQuestionMapper;
		
		//question detail
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
		
		//question update
		@Test
		public void testOfanswer() {
			PostDTO params = new PostDTO();
			params.setAnswer("답변이 추가되었습니다.");
			params.setModifier("관리자");
			params.setPostId((int)1);
			
			int result = adminQuestionMapper.updatePost(params);
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
		
		//question 
		@Test
		public void testSelectList() {
			PostDTO params = new PostDTO();
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
		
		
		
		
		
		
		@Autowired
		private Admin_LevelupTestMapper adminLevelupTestMapper;
		
		@Test
		public void levelupSelectList() {
			//given
			LevelupTestDTO levelup = new LevelupTestDTO();
			
			//when
			int totalCount = adminLevelupTestMapper.selectLevelupTestTotalCount(levelup);
			if(totalCount > 0) {
				List<LevelupTestDTO> levelList = adminLevelupTestMapper.selectLevelupTestList(levelup);
				if(CollectionUtils.isEmpty(levelList) == false) {
					//then
					for(LevelupTestDTO row : levelList) {
						System.out.println(row.getMbrId());
						System.out.println(row.getExerciseId());
						System.out.println(row.getTestDate());
					}
				}
			}
		}
		
		@Test
		public void hashMapTest() {
			//given
			Map<String, Object> map = new HashMap<>();
			
			//when
			map.put("testDate", "2021-11-05 11:39:26");
			map.put("mbrId", "test1");
			map.put("exerciseId", 7);
			
			System.out.println(map);
			
			LevelupTestDTO levelup = adminLevelupTestMapper.selectLevelupDetail(map);
		}

}
