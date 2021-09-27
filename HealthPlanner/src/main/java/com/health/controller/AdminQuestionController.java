package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.health.domain.PostDTO;
import com.health.service.AdminQuestionService;

@Controller
public class AdminQuestionController {

	@Autowired
	private AdminQuestionService adminQuestionService;
	
	@GetMapping(value="admin_question.do")
	public String adminQuestion(@ModelAttribute("params") PostDTO params, Model model) {
		List<PostDTO> postList = adminQuestionService.getPostList(params);
		model.addAttribute("postList",postList);
			
		return "admin_question";
	}

	
	// 게시판 GET
	@GetMapping(value="getBoard.do")
	public String sortTypeFirst(@ModelAttribute("params") PostDTO postDto, Model model) {
		System.out.println("GetMapping getBoard.do 요청할 때 넘어온 params : " + postDto);
		
		List<PostDTO> postList = adminQuestionService.getPostList(postDto);
		model.addAttribute("postList",postList);
		
		return "__TEST_GetBoard";
	}
		
	
	
	// 게시판 POST
	@GetMapping(value="postBoard.do")
	public String testpage2(@ModelAttribute("params") PostDTO postDto, Model model) {
		
		System.out.println("getmapping testList.do 요청할 때 넘어온 params : " + postDto);
		
		List<PostDTO> postList = adminQuestionService.getPostList(postDto);
		model.addAttribute("postList",postList);
		
		return "__TEST_PostBoard";
	}
	
	
	@PostMapping(value="postSort.do")
	public String sort2(@ModelAttribute("params") PostDTO postDto, Model model) {
		
		System.out.println("postmapping sort.do 요청할 떄 넘어온 params : " + postDto);
		
		List<PostDTO> postList = adminQuestionService.getPostList(postDto);
		model.addAttribute("postList",postList);
		
		return "__TEST_PostBoard :: #listTest";
	}

}
