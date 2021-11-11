package com.health.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.health.domain.MemberDTO;
import com.health.domain.MemberStateDTO;
import com.health.service.MemberStateService;
import com.health.util.UiUtils;

@Controller
public class MemberStateController extends UiUtils {

	@Autowired
	private MemberStateService memberstateService;
	//세션정보가져오기
	
	
	@GetMapping(value = "/memberstate/write.do")
	public String openMemberStateWrite(@ModelAttribute(value="memberState") MemberStateDTO memberState, MemberDTO member, Model model) {
		
		System.out.println("memberState : "+memberState);
		//memberDTO값 불러오는 메소드 필요
		//일단 id값 임의 지정 후 작성 기능 테스트
		if (member.getGoalRegister() == 0) {
			
			model.addAttribute("memberstate", memberState);		
		}
		else {
			return "redirect:main.do";
		}
		
		return "member_state";
	}
	
	@PostMapping(value = "/memberstate/register.do")
	public String registerMemberState(final MemberStateDTO memberState, HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("member");
		memberState.setMbrId(dto.getMbrId());
		System.out.println("등록중인 데이터" + memberState);
		
		//일단 id값 임의 지정 후 작성 기능 테스트
		try {
			boolean isRegistered = memberstateService.registerMemberState(memberState);
			if (isRegistered == false) {
				//return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/memberstate/list.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			//return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/memberstate/list.do", Method.GET, null, model);
		} catch (Exception e) {
			//return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/memberstate/list.do", Method.GET, null, model);
		}
		return showMessageWithRedirect("게시글이 등록되었습니다.", "/memberstate/list.do", Method.GET, null, model);
		//return "memberstatelist";
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
		if (memberstate == null) {
			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/memberstate/list.do";
		}
		model.addAttribute("memberstate", memberstate);

		return "memberstate/view.do";		
	}
	
	@PostMapping(value = "/memberstate/delete.do")
	public String deleteBoard(@RequestParam(value = "mbrId", required = false)String mbrId) {
		if (mbrId == null) {
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/memberstate/list.do";
		}

		try {
			boolean isDeleted = memberstateService.deleteMemberState(mbrId, mbrId);
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