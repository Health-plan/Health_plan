package com.health.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDbDTO {
	//아이디
	private String mbrId;
	//운동아이디
	private int exerciseId;
	//운동무게
	private int exerciseWeight;
	//운동횟수
	private int exerciseTime;
	//운동세트
	private int exerciseSet;
	//등급명
	private String levelNm;
	//등록자
	private String registrant;
	//등록시간
	private String registDate;
	//수정자
	private String modifier;
	//수정시간
	private String modifyDate;

}