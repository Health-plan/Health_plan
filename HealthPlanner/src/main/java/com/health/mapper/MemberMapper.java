package com.health.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.health.domain.MemberDTO;
import com.health.domain.UserDTO;

@Mapper
public interface MemberMapper {
	// 아이디중복체크
	public int idCheck(String mbr_id) throws Exception;
	 
	// 이메일중복체크
		public int emailCheck(String mbr_email) throws Exception;
		 
	
	//spring security 로그인 아이디읽기
	  public UserDTO readAccount(UserDTO userdto)throws Exception;

	
	  public String now() throws Exception;
	  
	
	 
	  // 회원가입 
	  public void memberRegister(MemberDTO memberDto) throws Exception;
	  
	 //아이디 찾
	  public MemberDTO findId(MemberDTO mbrdto) throws Exception;
	  
	  //비밀번호찾기 - hint-answer
	  public MemberDTO findPwhint(MemberDTO mbrdto) throws Exception;
	  public MemberDTO inputPwHintAnswer( MemberDTO mbrdto) throws Exception;
	  public void changeNewPw(MemberDTO mbrdto) throws Exception;
	  

}
