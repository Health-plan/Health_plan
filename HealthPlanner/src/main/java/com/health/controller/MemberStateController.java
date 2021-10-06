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

import com.health.domain.MemberDTO;
import com.health.domain.MemberStateDTO;
import com.health.service.MemberStateService;

@Controller
public class MemberStateController {

	@Autowired
	private MemberStateService memberstateService;
	//세션정보가져오기
	
	@GetMapping(value = "/memberstate/write.do")
	public String openMemberStateWrite(@ModelAttribute(value="memberstate") MemberStateDTO memberState, MemberDTO member, Model model) {
		System.out.println("이것은 memeberState값 : "+memberState);
		System.out.println("이것은 memeber값 : "+member);
		
		member.setMbrId("123");
		member.setGoalRegister(1);
		System.out.println("이것은 memeber값 : "+member);
		
		if (member.getMbrId() != null) {
			if(member.getGoalRegister() == 0) {
				memberState.setMbrId("123");
				model.addAttribute("memberstate", memberState);
				System.out.println("이것은 바뀐 memeber값 : "+memberState);
			}
		}
//		else {
//			MemberStateDTO memberstate = memberstateService.getMemberStateDetail(memberStateDate, "mbrId");
//			if (memberstate == null) {
//				return "redirect:/memberstate/list.do";
//			}
//			model.addAttribute("memberstate", memberstate);
//		}
		return "member_state";
	}
	
	@PostMapping(value = "/memberstate/register.do")
	public String registerMemberState(final MemberStateDTO memberState) {
		memberState.setMbrId("123");
		try {
			boolean isRegistered = memberstateService.registerMemberState(memberState);
			if (isRegistered == false) {
				// TODO => 게시글 등록에 실패하였다는 메시지를 전달
				System.out.println("1번 실패");
			}
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
			System.out.println("디비문제");
		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
			System.out.println("시스템문제");
		}

		return "redirect:/memberstate/list.do";
	}
	
	
	
	@GetMapping(value = "/memberstate/list.do")
	public String openMemberStateList(Model model,String mbrId,MemberDTO member) {
		member.setGoalRegister(1);
		
		List<MemberStateDTO> memberstateList = memberstateService.getMemberStateList(mbrId);
		model.addAttribute("memberstateList", memberstateList);

		return "memberstatelist";
	}
	
	
	
	@GetMapping(value = "/memberstate/view.do")
	public String openMemberStateDetail(@RequestParam(value = "mbrId", required = false) String mbrId, Model model) {
		if (mbrId == null) {
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/memberstate/list.do";
		}

		MemberStateDTO memberstate = memberstateService.getMemberStateDetail(mbrId);
		if (memberstate == null || "1".equals(memberstate.getModifyDate())) {
			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/memberstate/list.do";
		}
		model.addAttribute("memberstate", memberstate);

		return "memberstate/view.do";		
	}
	
	@PostMapping(value = "/memberstate/delete.do")
	public String deleteBoard(@RequestParam(value = "memberStateDate", required = false)String memberStateDate) {
		if (memberStateDate == null) {
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/memberstate/list.do";
		}

		try {
			boolean isDeleted = memberstateService.deleteMemberState(memberStateDate,"mbrId");
			if (isDeleted == false) {
				// TODO => 게시글 삭제에 실패하였다는 메시지를 전달
			}
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
		}

		return "redirect:/memberstate/list.do";
	}
}