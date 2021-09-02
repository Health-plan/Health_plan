package com.health.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.health.domain.MemberDAO;
import com.health.domain.MemberDTO;
import com.health.mapper.MemberMapper;

@Service

public class MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	
	//MemberDAO memberDao;

	

//	public String now() throws Exception {
//		return memberMapper.now();
//	}
//
//	// 로그인
//	public MemberDTO memberLogin(MemberDTO memberDto) throws Exception {
//		return memberMapper.memberLogin(memberDto);
//	}
//
//	// 유저체크
//	public MemberDTO userCheck(MemberDTO memberDto) throws Exception {
//		return memberMapper.userCheck(memberDto);
//	}

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
	

//	// 회원정보 수정 - 세션 가져오기
//	public MemberDTO membermodifyGET(String mbr_id) throws Exception {
//		return memberMapper.memberModifyGET(mbr_id);
//	}
//
//	// 회원정보 수정
//	public void memberModifyPOST(MemberDTO memberDto) throws Exception {
//		memberMapper.memberModifyPOST(memberDto);
//	}
//
//	// 회원 탈퇴
//	public void memberDelete(MemberDTO memberDto) throws Exception {
//		memberMapper.memberDelete(memberDto);
//	}

}