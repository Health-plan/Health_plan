package com.health.service;

import java.util.HashMap;
import java.util.List;

import com.health.domain.LevelupTestDTO;

public interface Admin_LevelupTestService {
	
	public LevelupTestDTO getTestDetail(HashMap<String, Object> map);
	
	public boolean confirmTest(LevelupTestDTO params);
	
	public List<LevelupTestDTO> getTestList(LevelupTestDTO params);

}
