package com.health.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.health.domain.MbrPointRecordDTO;

@Mapper
public interface Admin_PointMapper {
	
	//포인트목록
	public List<MbrPointRecordDTO> selectPointList(MbrPointRecordDTO params);
	//카운트
	public int selectPointTotalCount(MbrPointRecordDTO params);
	
	public List<MbrPointRecordDTO> selectPointDetailList(MbrPointRecordDTO params);
	
	public int selectPointDetailTotalCount(MbrPointRecordDTO params);
	
	public int selectPointTotal(MbrPointRecordDTO params);

}
