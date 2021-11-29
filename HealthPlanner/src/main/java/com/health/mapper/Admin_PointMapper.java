package com.health.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.health.domain.MbrPointRecordDTO;
import com.health.domain.PointPoliceDTO;

@Mapper
public interface Admin_PointMapper {
	
	//포인트 목록
	public List<MbrPointRecordDTO> selectPointList(MbrPointRecordDTO params);
	public int selectPointTotalCount(MbrPointRecordDTO params);
	
	//포인트 상세 목록
	public List<MbrPointRecordDTO> selectPointDetailList(MbrPointRecordDTO params);
	public int selectPointDetailTotalCount(MbrPointRecordDTO params);
	//포인트 적립/차감
	public int insertAdminPoint(MbrPointRecordDTO params);
	
	//포인트 정책 목록
	public List<PointPoliceDTO> selectPointPoliceList(PointPoliceDTO params);
	public int selectPointPoliceTotalCount(PointPoliceDTO params);
	//포인트 값 변경
	public int updatePointPoliceValue(PointPoliceDTO params);
	//포인트 정책 추가
	public int insertPointPolice(PointPoliceDTO params);
	
	//회원 현재포인트
	public int selectPointTotal(MbrPointRecordDTO params);

}
