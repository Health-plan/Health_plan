package com.health.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.health.domain.MemberDTO;
import com.health.domain.UserDTO;

@Mapper
public interface MemberMapper {
	// 아이디체크
	public int idCheck(String mbr_id) throws Exception;
	 
	//spring security 로그인 아이디읽기
	  public UserDTO readAccount(UserDTO userdto)throws Exception;

	
	  public String now() throws Exception;
	  
	 
	 
	  // 회원가입 
	  public void memberRegister(MemberDTO memberDto) throws Exception;
	  
	 
	 

}
