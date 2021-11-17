package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.health.domain.AdminMemberProgressVO;
import com.health.domain.GoalDTO;
import com.health.domain.MemberDTO;
import com.health.domain.SecessionDTO;
import com.health.mapper.Admin_MemberMapper;
import com.health.service.Admin_MemberService;

@Controller
public class Admin_MemberController {
	
	@Autowired
	private Admin_MemberService adminMemberService;
	
	@GetMapping(value="admin_member.do")
	public String adminMember(@ModelAttribute(value="params")MemberDTO params, Model model){
		
		List<MemberDTO> member = adminMemberService.getMemberList(params);
		model.addAttribute("member",member);
		
		return "Admin_Member";
	}
	
	@Autowired
	private Admin_MemberMapper adminMemberMapper;
	
	@GetMapping(value="admin_memberDetail.do")
	public String adminMemberDetail(@RequestParam(value="mbrId")String mbrId, Model model) {
		if(mbrId == null || mbrId=="") {
			return "redirect:Admin_member";
		}
		MemberDTO member = adminMemberService.getMemberDetail(mbrId);
		model.addAttribute("member",member);
		
		AdminMemberProgressVO progress = adminMemberService.getMemberProgress(mbrId);
		model.addAttribute("progress",progress);
		
		System.out.println(progress.getStart().getMemberFatper());
		
		
		return "Admin_MemberDetail";
	}
	
	
	@GetMapping(value="admin_memberSecession.do")
	public String adminMemberSecession(@ModelAttribute(value="params")SecessionDTO params, Model model) {
		
		List<SecessionDTO> secession = adminMemberService.getSecessionList(params);
		model.addAttribute("secession",secession);
		
		return "Admin_MemberSecession";
	}

}
