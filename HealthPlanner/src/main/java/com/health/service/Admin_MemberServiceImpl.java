package com.health.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.domain.AdminMemberProgressVO;
import com.health.domain.ExerciseDTO;
import com.health.domain.ExerciseDbDTO;
import com.health.domain.GoalDTO;
import com.health.domain.MemberDTO;
import com.health.domain.MemberStateDTO;
import com.health.domain.SecessionDTO;
import com.health.mapper.Admin_MemberMapper;
import com.health.paging.PaginationInfo;

@Service
public class Admin_MemberServiceImpl implements Admin_MemberService{
	
	@Autowired
	private Admin_MemberMapper adminMemberMapper;	
	
	//회원목록
	@Override
	public List<MemberDTO> getMemberList(MemberDTO params){
		List<MemberDTO> member = Collections.emptyList();
		
		int memberTotalCount = adminMemberMapper.selectMemberListTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(memberTotalCount);
		
		params.setPaginationInfo(paginationInfo);
		
		if(memberTotalCount > 0) {
			member = adminMemberMapper.selectMemberList(params);
		}
		
		return member;
	}
	
	//회원상세 기본정보
	@Override
	public MemberDTO getMemberDetail(String mbrId) {
		return adminMemberMapper.selectMemberDetail(mbrId);
	};
	
	//회원상세 진행현황
	@Override
	public AdminMemberProgressVO getMemberProgress(String mbrId) {
		AdminMemberProgressVO MemberProgress = new AdminMemberProgressVO();
		
		MemberStateDTO start = adminMemberMapper.selectProgressStart(mbrId);
		MemberStateDTO progress = adminMemberMapper.selectProgress(mbrId);
		GoalDTO goal = adminMemberMapper.selectProgressGoal(mbrId);
		
		MemberProgress.setStart(start);
		
		if(goal != null) {
			MemberProgress.setProgress(progress);
			MemberProgress.setGoal(goal);
			System.out.println("목표값이 없습니다.");
		}
		
		return MemberProgress;
	};
	
	//종목별 등급
	@Override
	public List<ExerciseDTO> getExerciseRank(String mbrId){
		List<ExerciseDTO> exerciseRank = Collections.emptyList();
		exerciseRank = adminMemberMapper.selectExerciseRank(mbrId);
		
		return exerciseRank;
	}
	;

	//탈퇴회원 목록
	@Override
	public List<SecessionDTO> getSecessionList(SecessionDTO params){
		List<SecessionDTO> secession = Collections.emptyList();
		
		int secessionTotalCount = adminMemberMapper.selectSecessionTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(secessionTotalCount);
		
		params.setPaginationInfo(paginationInfo);
		
		if(secessionTotalCount > 0) {
			secession = adminMemberMapper.selectSecessionList(params);
		}
		
		return secession;
	}

}
