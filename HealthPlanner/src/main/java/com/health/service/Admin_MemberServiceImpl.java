package com.health.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.domain.SecessionDTO;
import com.health.mapper.Admin_MemberMapper;
import com.health.paging.PaginationInfo;

@Service
public class Admin_MemberServiceImpl implements Admin_MemberService{
	
	@Autowired
	private Admin_MemberMapper adminMemberMapper;
	
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
