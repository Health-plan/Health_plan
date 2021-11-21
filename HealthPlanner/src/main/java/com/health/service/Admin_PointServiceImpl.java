package com.health.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.domain.MbrPointRecordDTO;
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
			System.out.println("포인트목록 총 갯수는" + pointTotalCount + "입니다.");
			point = adminPointMapper.selectPointList(params);
		}
		
		return point;
	};
	
}
