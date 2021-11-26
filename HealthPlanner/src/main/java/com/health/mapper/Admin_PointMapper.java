package com.health.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.health.domain.MbrPointRecordDTO;
import com.health.domain.PointPoliceDTO;

@Mapper
public interface Admin_PointMapper {
	
	//포인트목록
	public List<MbrPointRecordDTO> selectPointList(MbrPointRecordDTO params);
	public int selectPointTotalCount(MbrPointRecordDTO params);
	
	//포인트목록
	public List<MbrPointRecordDTO> selectPointDetailList(MbrPointRecordDTO params);
	public int selectPointDetailTotalCount(MbrPointRecordDTO params);
	
	public List<PointPoliceDTO> selectPointPoliceList(PointPoliceDTO params);
	public int selectPointPoliceTotalCount(PointPoliceDTO params);
	
	//회원 현재포인트
	public int selectPointTotal(MbrPointRecordDTO params);

}
