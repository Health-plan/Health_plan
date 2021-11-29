package com.health.service;

import java.util.List;

import com.health.domain.MbrPointRecordDTO;
import com.health.domain.PointPoliceDTO;

public interface Admin_PointService {
	//목록
	public List<MbrPointRecordDTO> getPointList(MbrPointRecordDTO params); //포인트 적립
	
	//상세
	public List<MbrPointRecordDTO> getPointDetailList(MbrPointRecordDTO params); // 상세목록	
	public boolean addPointRecord(MbrPointRecordDTO params); //포인트 적립/차감
	
	//정책
	public List<PointPoliceDTO> getPointPoliceList(PointPoliceDTO params); //목록
	public boolean modifyPointPolice(PointPoliceDTO params); //변경
	public boolean addPointPolice(PointPoliceDTO params); //추가

}
