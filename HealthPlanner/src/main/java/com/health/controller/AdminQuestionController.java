package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
	
	@GetMapping(value="test.do")
	public String testpage(@ModelAttribute("params") PostDTO params, Model model) {
		List<PostDTO> postList = adminQuestionService.getPostList(params);
		model.addAttribute("postList",postList);
		
		
		model.addAttribute("sortType",params.getPaginationInfo().getSortType());
		System.out.println(params.getPaginationInfo().getSortType());
		
		return "test";
		
	}
	
	@GetMapping(value="test2.do")
	public String testpage2(Model model) {
		return "test2";
	}
}
