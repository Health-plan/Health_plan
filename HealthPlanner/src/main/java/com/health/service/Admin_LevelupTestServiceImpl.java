package com.health.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.domain.LevelupTestDTO;
import com.health.mapper.Admin_LevelupTestMapper;
import com.health.paging.PaginationInfo;

@Service
public class Admin_LevelupTestServiceImpl implements Admin_LevelupTestService{

	@Autowired
	private Admin_LevelupTestMapper adminLevelupTestMapper;
	
	public LevelupTestDTO getTestDetail(HashMap<String, Object> map) {
		return adminLevelupTestMapper.selectLevelupDetail(map);
	};
	
	public boolean confirmTest(LevelupTestDTO params) {
		
		int queryResult = 0;
		
		queryResult = adminLevelupTestMapper.updateLevelupTest(params);
		
		return (queryResult == 1) ? true : false;
	};
	
	public List<LevelupTestDTO> getTestList(LevelupTestDTO params){
		//비어있는 List의 경우에도 return 할 수 있음
		List<LevelupTestDTO> levelup = Collections.emptyList();
		
		int levelupTotalCount = adminLevelupTestMapper.selectLevelupTestTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(levelupTotalCount);
		
		params.setPaginationInfo(paginationInfo);
		
		if(levelupTotalCount > 0) {
			levelup = adminLevelupTestMapper.selectLevelupTestList(params);
		}
		
		return levelup;
	};

}
