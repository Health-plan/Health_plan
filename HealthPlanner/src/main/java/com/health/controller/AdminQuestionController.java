package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.health.domain.PostDTO;
import com.health.paging.Criteria;
import com.health.service.AdminQuestionService;

@Controller
public class AdminQuestionController {

	@Autowired
	private AdminQuestionService adminQuestionService;
	
	@GetMapping(value="admin_question.do")
		public String adminQuestion(@ModelAttribute("criteria") Criteria criteria, Model model) {
			List<PostDTO> postList = adminQuestionService.getPostList(criteria);
			model.addAttribute("postList",postList);
			
			return "admin_question";
	}
	
	@GetMapping(value="test.do")
		public String testpage(Model model) {
		return "test";
	}
}
