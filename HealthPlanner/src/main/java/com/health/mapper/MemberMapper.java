package com.health.mapper;

import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MemberMapper {
	// 아이디체크
	public int idCheck(String mbr_id) throws Exception;
	
	
	/*
	 * public String now() throws Exception;
	 * 
	 * // 로그인 public MemberDTO memberLogin(MemberDTO memberDto) throws Exception;
	 * 
	 * // 유저체크 public MemberDTO userCheck(MemberDTO memberDto) throws Exception;
	 * 
	 * 
	 * 
	 * 
	 * // 회원가입 public void memberRegister(MemberDTO memberDto) throws Exception;
	 * 
	 * // 회원정보 수정 - 세션 가져오기 public MemberDTO memberModifyGET(String mbr_id) throws
	 * Exception;
	 * 
	 * // 회원정보 수정 public void memberModifyPOST(MemberDTO memberDto) throws
	 * Exception;
	 * 
	 * // 회원 탈퇴 public void memberDelete(MemberDTO memberDto) throws Exception;
	 */

}
