package com.health.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LevelPointDTO {
	//레벨아이디
	private int levelId;
	//등급명
	private String levelNm;
	//상위분포
	private long levelPer;
	//아이콘
	private String icon;
	//등록자
	private String registrant;
	//등록일시
	private String registDate;
	//수정자
	private String modifier;
	//수정일시
	private String modifyDate;
	
}
