package com.health.service;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.health.domain.MemberDTO;
import com.health.domain.PointDTO;
import com.health.domain.UserDTO;
import com.health.mapper.MemberMapper;
import com.health.paging.PaginationInfo;

@Service

public class MemberService{

	
	
	@Autowired
	MemberMapper memberMapper;
	
	public MemberDTO readAccount(MemberDTO userdto, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		System.out.println(userdto + " memberservice에 readAccount함수로 들어옴");
		HttpSession session = req.getSession();
		
		MemberDTO login =memberMapper.readAccount(userdto);
		
		
		 if(login == null) {
			  session.setAttribute("member", null);
			  rttr.addFlashAttribute("msg", false);
			  
			 } else {
			  session.setAttribute("member", login);
			  System.out.println("member : " + session.getAttribute("member"));
			  System.out.println("멤버세션값"+req.getSession().getAttribute("member"));
			  
			 }
		
		System.out.println("member /" + session +"/?/"+ session.getAttribute("member"));
		
		return login;
	}



	// 아이디중복체크

	public int idCheck(String mbr_id) throws Exception {
		System.out.println(mbr_id + "memberservice에 idcheck함수로 들어옴");
		return memberMapper.idCheck(mbr_id);
	}
	
	// 이메일체크

		public int emailCheck(String mbr_email) throws Exception {
			System.out.println(mbr_email + "memberservice에 idcheck함수로 들어옴");
			return memberMapper.emailCheck(mbr_email);
		}
		// 마이페이지 비밀번호체크

		public MemberDTO mypagePasswordCheck(MemberDTO mbr_pw) throws Exception {
				System.out.println(mbr_pw + "memberservice에 mypagePasswordCheck함수로 들어옴");
				return memberMapper.mypagePasswordCheck(mbr_pw);
		}


	// 회원가입
	public void memberRegister(MemberDTO memberDto) throws Exception {
		
		//memberDto.setMbrPhoto("/static/image/국밥.png");
		//System.out.println("컴파일확인"+memberDto);
		memberMapper.memberRegister(memberDto);
		
	}
	
	//아이디찾기
	public MemberDTO findId(MemberDTO mbrdto, RedirectAttributes rttr) throws Exception
	{
		MemberDTO inputEmailMiss = memberMapper.findId(mbrdto);
		System.out.println(mbrdto + "memberservice에 findid함수로 들어옴");
		if(inputEmailMiss == null)
		{
			rttr.addFlashAttribute("emailForId", false);
		}
		return inputEmailMiss;
	}
	
	//비밀번호찾기
		public MemberDTO findPwhint(MemberDTO mbrdto, RedirectAttributes rttr) throws Exception
		{
			//입력된 아이디에 맞는 데이터가 없을때
			MemberDTO inputIdMiss = memberMapper.findPwhint(mbrdto);
			System.out.println(mbrdto + "memberservice에 findpwhint함수로 들어옴");
			if(inputIdMiss == null)
			{
				rttr.addFlashAttribute("idForPw", false);
			}
			return inputIdMiss;
		}
		
	//비밀번호힌트 답 입력
		public MemberDTO inputPwHintAnswer(MemberDTO mbrdto, RedirectAttributes rttr) throws Exception
		{
			MemberDTO inputPwAnswer = memberMapper.inputPwHintAnswer(mbrdto);
			 if(inputPwAnswer == null) {
				
				  rttr.addFlashAttribute("pwhintAnswer", false);
				  
				 } 
			System.out.println(mbrdto + "memberservice에 FindPwHintAnswer함수로 들어옴");
			return inputPwAnswer;
		}
		
	//새비밀번호로 변경
		public void changeNewPw(MemberDTO mbrdto) throws Exception
		{
			System.out.println(mbrdto + "memberservice에 changeNewPw함수로 들어옴");
			memberMapper.changeNewPw(mbrdto);
		}
		
	//새비밀번호로 변경 - mypage
		public void changeNewPwInMyPage(MemberDTO mbrdto) throws Exception
		{
			System.out.println(mbrdto + "memberservice에 changeNewPwInMyPage함수로 들어옴");
			memberMapper.changeNewPwInMyPage(mbrdto);
		}
				
		
		//포인트 리스트 출력
		 public List<PointDTO> pointContentsList(MemberDTO mbrdto) throws Exception
		 {
			 List<PointDTO> pList= Collections.emptyList();
			 int PointTotalCount = memberMapper.selectPointTotalCount(mbrdto);
			 System.out.println(mbrdto + "포인트리스트에 pointcontentstlist함수로 들어옴"+PointTotalCount);
			 PaginationInfo paginationInfo = new PaginationInfo(mbrdto);
				paginationInfo.setTotalRecordCount(PointTotalCount);

				mbrdto.setPaginationInfo(paginationInfo);	
			 
			 if(PointTotalCount >0)
				{
					pList= memberMapper.pointContentsList(mbrdto);
				}
				return pList;
		 }
		 
		 public int pointValueTotal(MemberDTO mbrdto) throws Exception
		 {
			 int pTotal= memberMapper.pointValueTotal(mbrdto);
			 System.out.println(mbrdto + "포인트리스트에 pointValueTotal함수로 들어옴");
				
				return pTotal;
		 }



}