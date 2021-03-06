package com.health.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.health.paging.Criteria;
import com.health.domain.MemberDTO;
import com.health.domain.PointDTO;


@Mapper
public interface MemberMapper {
	// 아이디중복체크
	public int idCheck(String mbr_id) throws Exception;
	 
	// 이메일중복체크
		public int emailCheck(String mbr_email) throws Exception;
		 
	// 마이페이지 비밀번호체크
		public MemberDTO mypagePasswordCheck(MemberDTO mbr_pw) throws Exception;
	
	//spring security 로그인 아이디읽기
	  public MemberDTO readAccount(MemberDTO userdto)throws Exception;

	
	  public String now() throws Exception;
	  
	
	 
	  // 회원가입 
	  public void memberRegister(MemberDTO memberDto) throws Exception;
	  
	 //아이디 찾기
	  public MemberDTO findId(MemberDTO mbrdto) throws Exception;
	  
	  //비밀번호찾기 - hint-answer
	  public MemberDTO findPwhint(MemberDTO mbrdto) throws Exception;
	  public MemberDTO inputPwHintAnswer( MemberDTO mbrdto) throws Exception;
	  public void changeNewPw(MemberDTO mbrdto) throws Exception;
	  public void changeNewPwInMyPage(MemberDTO mbrdto) throws Exception;
	  
	  public List<PointDTO> pointContentsList(MemberDTO mbrdto) throws Exception;
	  public int pointValueTotal(MemberDTO mbrdto) throws Exception;


	public int selectPointTotalCount(MemberDTO mbrdto)throws Exception;

}
