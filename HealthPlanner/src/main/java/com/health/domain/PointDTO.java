package com.health.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointDTO {
	//id
	private String mbrId;
	//포인트 고유번호
	private int pointId;
	//포인트 증,차감 일시
	private String PointDate;
	//증 차감된 포인트값
	private int pointValue;
	//포인트 증차감 내용
	private String pointContents;
	// 포인트 증감인지 차감인지
	private String pointCategory;
	
	
}
