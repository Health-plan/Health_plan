package com.health.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.domain.MbrPointRecordDTO;
import com.health.domain.PointPoliceDTO;
import com.health.mapper.Admin_PointMapper;
import com.health.paging.PaginationInfo;

@Service
public class Admin_PointServiceImpl implements Admin_PointService{

	@Autowired
	private Admin_PointMapper adminPointMapper;
	
	@Override
	public List<MbrPointRecordDTO> getPointList(MbrPointRecordDTO params){
		List<MbrPointRecordDTO> point = Collections.emptyList();
		int pointTotalCount = adminPointMapper.selectPointTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(pointTotalCount);
		
		params.setPaginationInfo(paginationInfo);
		
		if(pointTotalCount > 0) {
			point = adminPointMapper.selectPointList(params);
		}
		
		return point;
	};
	
	@Override
	public List<MbrPointRecordDTO> getPointDetailList(MbrPointRecordDTO params){
		List<MbrPointRecordDTO> pointDetail = Collections.emptyList();
		int pointDetailTotalCount = adminPointMapper.selectPointDetailTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(pointDetailTotalCount);
		
		params.setPaginationInfo(paginationInfo);
		
		if(pointDetailTotalCount > 0) {
			pointDetail = adminPointMapper.selectPointDetailList(params);
		}
		
		return pointDetail;
	};
	
	@Override
	public List<PointPoliceDTO> getPointPoliceList(PointPoliceDTO params){
		List<PointPoliceDTO> police = Collections.emptyList();
		
		int pointPoliceTotalCount = adminPointMapper.selectPointPoliceTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(pointPoliceTotalCount);
		
		params.setPaginationInfo(paginationInfo);
		
		if(pointPoliceTotalCount > 0) {
			police = adminPointMapper.selectPointPoliceList(params);
		}
		
		return police;
	};
	
}
