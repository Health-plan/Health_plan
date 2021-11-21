package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.health.domain.AdminMemberProgressVO;
import com.health.domain.ExerciseDTO;
import com.health.domain.ExerciseDbDTO;
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
		//상세기본정보
		MemberDTO member = adminMemberService.getMemberDetail(mbrId);
		model.addAttribute("member",member);
		//진행현황
		AdminMemberProgressVO progress = adminMemberService.getMemberProgress(mbrId);
		model.addAttribute("progress",progress);
		//회원 종목별 등급
		List<ExerciseDTO> exerciseRank = adminMemberMapper.selectExerciseRank(mbrId);
		model.addAttribute("exerciseRank",exerciseRank);
		
		
		return "Admin_MemberDetail";
	}
	
	
	@GetMapping(value="admin_memberSecession.do")
	public String adminMemberSecession(@ModelAttribute(value="params")SecessionDTO params, Model model) {
		
		List<SecessionDTO> secession = adminMemberService.getSecessionList(params);
		model.addAttribute("secession",secession);
		
		return "Admin_MemberSecession";
	}

}
