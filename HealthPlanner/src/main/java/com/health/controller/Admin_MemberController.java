package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.health.domain.SecessionDTO;
import com.health.service.Admin_MemberService;

@Controller
public class Admin_MemberController {
	
	@Autowired
	private Admin_MemberService adminMemberService;
	
	@GetMapping(value="admin_memberSecession.do")
	public String adminMember(@ModelAttribute(value="params")SecessionDTO params, Model model) {
		
		List<SecessionDTO> secession = adminMemberService.getSecessionList(params);
		model.addAttribute("secession",secession);
		
		return "Admin_MemberSecession";
	}

}
