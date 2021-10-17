package com.health.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.health.domain.PostDTO;
import com.health.service.Admin_QuestionService;

//Admin_Question Page
@Controller
public class Admin_QuestionController {

	@Autowired
	private Admin_QuestionService adminQuestionService;

	//List
	@GetMapping(value="admin_Question.do")
	public String adminQuestion(@ModelAttribute("params") PostDTO postDto, Model model, HttpSession pageStatus){ 
		//세션 생성
		pageStatus.setAttribute("currentPageNo", postDto.getCurrentPageNo());
		pageStatus.setAttribute("sortType", postDto.getSortType());
		System.out.println("세션값 - currentPageNo : " + pageStatus.getAttribute("currentPageNo"));
		System.out.println("세션값 - sortType : " + pageStatus.getAttribute("sortType"));
		
		//작성일자에서 사용할 현재 정보
		LocalDate nowDate = LocalDate.now();
		model.addAttribute("nowDate",nowDate);
		
		//리스트 출력
		List<PostDTO> postList = adminQuestionService.getPostList(postDto);
		model.addAttribute("postList",postList);
		
		
		return "Admin_Question";
	}
		
	//Detail
	@GetMapping(value="admin_QuestionDetail.do")
	public String adminQuestionDetail(@RequestParam(value="postId", required = false)int postId, Model model) {
		if(postId == 0) {
			//TODO => 올바르지 않은 접근이라는 메시지를 전달, 게시글 리스트로 리다이렉트
			return "redirect:Admin_Question.do";
		}
		PostDTO post = adminQuestionService.getPostDetail(postId);

		if(post == null || post.getAvailable() == 1) {
			//TODO => 없는 게시글이거나, 삭제된 게시글이라는 메시지를 전달, 리스트로 리다이렉트
			return "redirect:Admin_Question.do";
		}
		model.addAttribute("post",post);
		//답변 update에서 사용할 postId값
		model.addAttribute("postId",postId);

		return "Admin_QuestionDetail";
	}
	
	//Answer & Modify
	@PostMapping(value="admin_QuestionDetailAnswer.do")
	public String adminQuestionDetailAnswer(@RequestParam(value="postId", required = false)int postId, PostDTO post, Model model) {
		//postId 정보가 없으면 리스트페이지로 이동
		if(postId == 0) {
			return "redirect:admin_Question.do";
		}
		//update 답변 메소드 실행
		try {
			boolean isUpdated =	adminQuestionService.adminQuestionAnswer(post);
			if(isUpdated == false) {
				System.out.println("메소드 문제");
			}
		} catch(DataAccessException e){
			System.out.println("DB 오류 : " + e);
		} catch(Exception e) {
			System.out.println("시스템 오류 : " + e);
		} 
		
		return "redirect:admin_QuestionDetail.do?postId=" + post.getPostId();
	}
}
