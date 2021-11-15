package com.health.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecessionDTO extends CommonDTO {
	
	//식별자
	private int secId;
	//아이디
	private String mbrId;
	//탈퇴이유
	private String secReason;
	//작성자
	private String registrant;
	//작성일자
	private String registDate;
	//수정자
	private String modifier;
	//수정일자
	private String modifyDate;

}
