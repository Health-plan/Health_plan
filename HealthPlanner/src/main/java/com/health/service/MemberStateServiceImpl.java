package com.health.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.domain.MemberStateDTO;
import com.health.mapper.MemberStateMapper;

@Service
public class MemberStateServiceImpl implements MemberStateService {

	@Autowired
	private MemberStateMapper memberStateMapper;

	@Override
	public boolean registerMemberState(MemberStateDTO params) {
		int queryResult = 0;
		
		if (params.getMbrId() == null) {
			queryResult = memberStateMapper.insertMemberState(params);
			System.out.println("insert메소드값 : "+ queryResult);
		} else {
			queryResult = memberStateMapper.updateMemberState(params);
			System.out.println("update메소드값 : "+ queryResult);
		}

		return (queryResult == 1) ? true : false;
	}


	@Override
	public MemberStateDTO getMemberStateDetail(String mbrId) {
		return memberStateMapper.selectMemberStateDetail(mbrId);
	}
	
	@Override
	public boolean deleteMemberState(String memberStateDate,String mbrId) {
		int queryResult = 0;

		MemberStateDTO memberState = memberStateMapper.selectMemberStateDetail(mbrId);

		if (memberState != null && "0".equals(memberState.getModifyDate())) {
			queryResult = memberStateMapper.deleteMemberState(memberStateDate, mbrId);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<MemberStateDTO> getMemberStateList(String mbrId) {
		List<MemberStateDTO> memberStateList = Collections.emptyList();

		String memberStateTotalCount = memberStateMapper.selectMemberStateTotalCount();

		if (memberStateTotalCount != null) {
			memberStateList = memberStateMapper.selectMemberStateList(mbrId);
		}

		return memberStateList;
	}

}