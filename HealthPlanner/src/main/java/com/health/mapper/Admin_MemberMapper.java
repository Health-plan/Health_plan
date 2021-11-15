package com.health.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.health.domain.SecessionDTO;

@Mapper
public interface Admin_MemberMapper {

	public List<SecessionDTO>selectSecessionList(SecessionDTO params);
	
	public int selectSecessionTotalCount(SecessionDTO params);
}
