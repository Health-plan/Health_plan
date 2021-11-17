package com.health.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.health.domain.GoalDTO;
import com.health.domain.MemberDTO;
import com.health.domain.MemberStateDTO;
import com.health.domain.SecessionDTO;

@Mapper
public interface Admin_MemberMapper {
	
	//회원목록
	public List<MemberDTO>selectMemberList(MemberDTO params);
	public int selectMemberListTotalCount(MemberDTO params);
	//상세(기본)
	public MemberDTO selectMemberDetail(String mbrId);
	//진행현황
	public MemberStateDTO selectProgressStart(String mbrId);
	public MemberStateDTO selectProgress(String mbrId);
	public GoalDTO selectProgressGoal(String mbrId);
	//탈퇴회원
	public List<SecessionDTO>selectSecessionList(SecessionDTO params);
	public int selectSecessionTotalCount(SecessionDTO params);
	
}
