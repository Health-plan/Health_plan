package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.health.domain.PostDTO;
import com.health.service.Admin_QnAService;

//관리자페이지 - QnA
@Controller
public class Admin_QnAController {

	@Autowired
	private Admin_QnAService adminQnAService;
	
	//QnA리스트
	@GetMapping(value="admin_QnA.do")
	public String adminQnA(@ModelAttribute("params") PostDTO postDto, Model model) {
		
		List<PostDTO> post = adminQnAService.getQnAList(postDto);
		model.addAttribute("postList",post);
		
		return "Admin_QnA";
	}
	
	//상세페이지
	@GetMapping(value="admin_QnADetail.do")
	public String adminQnADetail(@RequestParam(value="postId", required=false)int postId, Model model) {
		if(postId == 0) {
			return "redirect:admin_QnA.do";
		}
		PostDTO post = adminQnAService.getQnADetail(postId);
		if(post == null || post.getAvailable() == 1) {
			return "redirect:admin_QnA.do";
		}
		model.addAttribute("post",post);
		
		return "Admin_QnADetail";
	}
	
	@PostMapping(value="admin_QnAWriteContain.do")
	public String adminQnADetailWrite(@RequestParam(value="postId", required=false)int postId, int writeContain, Model model) {
		model.addAttribute("writeContain",writeContain);
		
		PostDTO post = adminQnAService.getQnADetail(postId);
		model.addAttribute("post",post);
		
		return "Admin_QnADetail :: #writeAnswer";
	}
	
	//수정	
	@GetMapping(value="admin_QnAUpdate.do")
	public String adminQnADetailUp(@RequestParam(value="postId", required=false)int postId, PostDTO post, Model model) {
		adminQnAService.writeQnA(post);
		post = adminQnAService.getQnADetail(postId);
		model.addAttribute("post",post);
		
		return "redirect:admin_QnADetail.do?postId="+post.getPostId();
	}
	
	//삭제
	@PostMapping(value="admin_QnADelete.do")
	public String adminQnADelete(@RequestParam(value="postId", required=false)int postId) {
		System.out.println("삭제 페이지");
		if(postId == 0) {
			return "redirect:admin_QnA.do";
		}
		
		try {
			boolean isDeleted = adminQnAService.deleteQnA(postId);
			if(isDeleted == false) {
				System.out.println("메소드 문제 : " + isDeleted);
			}
		} catch (DataAccessException e) {
			System.out.println("DB처리에서 문제 : " + e);
		} catch (Exception e) {
			System.out.println("시스템 처리에서 문제 : " + e);
		}
		
		return "redirect:admin_QnA.do";
	}
	
	//작성
	@GetMapping(value="admin_QnAWrite.do")
	public String adminQnAWrite(Model model) {
		model.addAttribute("post",new PostDTO());
		
		return "Admin_QnAWrite";
	}
	
	@PostMapping(value="admin_QnAWriting.do")
	public String adminQnAWriting(final PostDTO post, Model model) {
		System.out.println("이것은 보내지는 post인스턴스" + post);
		try{
			boolean isInserted = adminQnAService.writeQnA(post);
			if(isInserted == false) {
				System.out.println("메소드 오류 : " + isInserted);
			}
		} catch (DataAccessException e) {
			System.out.println("데이터베이스 예외 : " + e);
		} catch(Exception e) {
			System.out.println("시스템 예외 : " + e);
		}
		
		return "redirect:admin_QnA.do";
	}
	
	
}
