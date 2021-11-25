package com.health.service;

import java.util.List;

import com.health.domain.MbrPointRecordDTO;

public interface Admin_PointService {
	
	public List<MbrPointRecordDTO> getPointList(MbrPointRecordDTO params);
	
	public List<MbrPointRecordDTO> getPointDetailList(MbrPointRecordDTO params);

}
