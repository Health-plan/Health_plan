package com.health.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.health.domain.Account;
import com.health.domain.MemberDAOImpl;
import com.health.domain.MemberDTO;
import com.health.mapper.MemberMapper;

@Service

public class MemberService implements UserDetailsService{

	
	
	@Autowired
	MemberMapper memberMapper;
	
	
	@Autowired
	MemberDAOImpl memberDao;
	
	// 로그인
	public UserDetails loadUserByUsername(String mbr_id) throws UsernameNotFoundException {
		
		
		Account account = memberDao.findById(mbr_id);
		System.out.println(mbr_id + "|  memberservice에 findByID함수로 들어옴");
		if( account == null ) {
			System.out.println("## 계정정보가 존재하지 않습니다. ##");
			throw new UsernameNotFoundException(mbr_id);
		}
		return account;
		
	
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