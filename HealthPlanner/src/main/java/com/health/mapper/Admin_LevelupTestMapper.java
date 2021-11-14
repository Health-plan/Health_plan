package com.health.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.health.domain.LevelupTestDTO;

@Mapper
public interface Admin_LevelupTestMapper {
	
	public LevelupTestDTO selectLevelupDetail(Map<String, Object> map);
	
	public int updateLevelupTest(LevelupTestDTO params);
	
	public List<LevelupTestDTO> selectLevelupTestList(LevelupTestDTO params);
	
	public int selectLevelupTestTotalCount(LevelupTestDTO params);
	
}