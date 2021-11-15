package com.health.service;

import java.util.List;

import com.health.domain.SecessionDTO;

public interface Admin_MemberService {
	
	//탈퇴회원 리스트
	public List<SecessionDTO> getSecessionList(SecessionDTO params);
}
