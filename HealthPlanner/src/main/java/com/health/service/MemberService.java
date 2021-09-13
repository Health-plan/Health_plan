package com.health.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.health.domain.MemberDTO;
import com.health.domain.UserDTO;
import com.health.mapper.MemberMapper;

@Service

public class MemberService{

	
	
	@Autowired
	MemberMapper memberMapper;
	
	public UserDTO readAccount(UserDTO userdto, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		System.out.println(userdto + " memberservice에 readAccount함수로 들어옴");
		HttpSession session = req.getSession();
		
		UserDTO login =memberMapper.readAccount(userdto);
		
		 if(login == null) {
			  session.setAttribute("member", null);
			  rttr.addFlashAttribute("msg", false);
			  
			 } else {
			  session.setAttribute("member", login);
			  System.out.println("멤버세션값"+req.getSession().getAttribute("member"));
			  
			 }
		
		System.out.println("member /" + session +"/?/"+ session.getAttribute("member"));
		
		return login;
	}



	// 아이디체크

	public int idCheck(String mbr_id) throws Exception {
		System.out.println(mbr_id + "memberservice에 idcheck함수로 들어옴");
		return memberMapper.idCheck(mbr_id);
	}

	// 회원가입
	public void memberRegister(MemberDTO memberDto) throws Exception {
		
		//memberDto.setMbrPhoto("/static/image/국밥.png");
		//System.out.println("컴파일확인"+memberDto);
		memberMapper.memberRegister(memberDto);
		
	}
	
	
	



}