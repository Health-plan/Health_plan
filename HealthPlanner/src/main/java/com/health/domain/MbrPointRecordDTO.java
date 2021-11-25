package com.health.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MbrPointRecordDTO extends CommonDTO{
	//포인트적립 일자
	private String pointDate;
	//아이디
	private String mbrId;
	//포인트식별자
	private int pointId;
	//작성자
	private String registrant;
	//수정자
	private String modifier;
	//수정일자
	private String modifyDate;
	
	private MemberDTO memberDTO;
	private PointPoliceDTO pointPoliceDTO;
	
}
