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

import com.health.constant.Method;
import com.health.domain.PostDTO;
import com.health.service.Admin_QuestionService;
import com.health.util.UiUtils;

//Admin_Question Page
@Controller
public class Admin_QuestionController extends UiUtils{

	@Autowired
	private Admin_QuestionService adminQuestionService;

	//List
	@GetMapping(value="admin.do")
	public String adminQuestion(@ModelAttribute("params") PostDTO postDto, Model model){
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
	public String adminQuestionDetail(@ModelAttribute("params") PostDTO params, @RequestParam(value="postId", required = false)int postId, Model model) {
		if(postId == 0) {
			//TODO => 올바르지 않은 접근이라는 메시지를 전달, 게시글 리스트로 리다이렉트
			return "redirect:admin.do";
		}
		PostDTO post = adminQuestionService.getPostDetail(postId);

		if(post == null || post.getAvailable() == 1) {
			//TODO => 없는 게시글이거나, 삭제된 게시글이라는 메시지를 전달, 리스트로 리다이렉트
			return "redirect:admin.do";
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
			return "redirect:admin.do";
		}
		//update 답변 메소드 실행
		try {
			boolean isUpdated =	adminQuestionService.adminQuestionAnswer(post);
			if(isUpdated == false) {
				System.out.println("메소드 문제");
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "admin.do", Method.GET, null, model);
			}
		} catch(DataAccessException e){
			System.out.println("DB 오류 : " + e);
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "admin.do", Method.GET, null, model);
		} catch(Exception e) {
			System.out.println("시스템 오류 : " + e);
			return showMessageWithRedirect("시스템에 문제가 발생했습니다.", "admin.do", Method.GET, null, model);
		} 
		
		return showMessageWithRedirect("게시글 답변이 등록되었습니다.", "admin_QuestionDetail.do?postId="+ post.getPostId(), Method.GET, null, model);
		
	}
}
