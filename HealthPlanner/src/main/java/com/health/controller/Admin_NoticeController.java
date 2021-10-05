package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.health.domain.MessageDTO;
import com.health.domain.PostDTO;
import com.health.service.Admin_NoticeService;

@Controller
public class Admin_NoticeController {

	@Autowired
	private Admin_NoticeService adminNoticeService;
	
	//공지리스트
	@GetMapping(value="admin_Notice.do")
	public String adminNotice(@ModelAttribute("params") PostDTO postDto, Model model) {
		
		List<PostDTO> post = adminNoticeService.getNoticeList(postDto);
		model.addAttribute("postList",post);
		
		return "Admin_Notice";
	}
	
	//상세페이지
	@GetMapping(value="admin_NoticeDetail.do")
	public String adminNoticeDetail(@RequestParam(value="postId", required=false)int postId, Model model) {
		if(postId == 0) {
			return "redirect:admin_Notice.do";
		}
		PostDTO post = adminNoticeService.getNoticeDetail(postId);
		if(post == null || post.getAvailable() == 1) {
			return "redirect:admin_Notice.do";
		}
		model.addAttribute("post",post);
		
		return "Admin_NoticeDetail";
	}
	
	
	@PostMapping(value="admin_NoticeWriteContain.do")
	public String adminNoticeDetailWrite(@RequestParam(value="postId", required=false)int postId, int writeContain, Model model) {
		model.addAttribute("writeContain",writeContain);
		
		PostDTO post = adminNoticeService.getNoticeDetail(postId);
		model.addAttribute("post",post);
		
		
		
		return "Admin_NoticeDetail :: #writeAnswer";
	}
	
	//수정	
	@PostMapping(value="admin_NoticeUpdate.do")
	public String adminNoticeDetailUp(@RequestParam(value="postId", required=false)int postId, int writeContain, PostDTO post, Model model) {
		model.addAttribute("writeContain",writeContain);
		
		adminNoticeService.writeNotice(post);
		post = adminNoticeService.getNoticeDetail(postId);
		model.addAttribute("post",post);
		
		return "Admin_NoticeDetail :: #writeAnswer";
	}
	
	@PostMapping(value="admin_NoticeDelete.do")
	public String adminNoticeDelete(@RequestParam(value="postId", required=false)int postId) {
		
		boolean isDeleted = adminNoticeService.deleteNotice(postId);
		
		
		return "redirect:Admin_NoticeDetail";
		
	}
}
