package com.health.service;

import java.util.List;

import com.health.domain.AdminMemberProgressVO;
import com.health.domain.ExerciseDTO;
import com.health.domain.ExerciseDbDTO;
import com.health.domain.MemberDTO;
import com.health.domain.SecessionDTO;

public interface Admin_MemberService {
	
	//회원 리스트
	public List<MemberDTO> getMemberList(MemberDTO params);
	//회원상세 기본정보
	public MemberDTO getMemberDetail(String mbrId);
	//회원상세 진행현황
	public AdminMemberProgressVO getMemberProgress(String mbrId);
	//종목별 등급
	public List<ExerciseDTO> getExerciseRank(String mbrId);
	//탈퇴회원 리스트
	public List<SecessionDTO> getSecessionList(SecessionDTO params);
	
}
