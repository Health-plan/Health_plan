package com.health.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.health.domain.MemberStateDTO;

@Mapper
public interface MemberStateMapper {

	public int insertMemberState(MemberStateDTO params);

	public MemberStateDTO selectMemberStateDetail(String mbrId);

	public int updateMemberState(MemberStateDTO params);
	
	public int deleteMemberState(String memberStateDate,String mbrId);

	public List<MemberStateDTO> selectMemberStateList(String mbrId);

	public String selectMemberStateTotalCount();



}
