package com.health.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointPoliceDTO {
	//포인트식별자
	private int pointId;
	//포인트값
	private int pointValue;
	//포인트내역
	private String pointContents;
	//포인트종류(증감)
	private String pointCategory;
	//작성자
	private String registrant;
	//작성일자
	private String registDate;
	//수정자
	private String modifier;
	//수정일자
	private String modifyDate;

}
