package com.health.service;

import java.util.List;

import com.health.domain.MemberDTO;
import com.health.domain.MemberStateDTO;

public interface MemberStateService {

	public boolean registerMemberState(MemberStateDTO member);

	public MemberStateDTO getMemberStateDetail(String mbrId);

	public boolean deleteMemberState(String memberStateDate,String mbrId);

	public List<MemberStateDTO> getMemberStateList(String mbrId);
}