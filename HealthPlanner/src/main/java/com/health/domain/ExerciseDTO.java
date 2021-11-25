package com.health.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDTO {
	
	//운동식별자
	private int exerciseId;
	//운동명
	private String exerciseNm;
	//운동내용
	private String exerciseContents;
	//주요 자극부위
	private String mainPart;
	//부수적 자극부위
	private String subPart;
	//등록자
	private String registrant;
	//등록일자
	private String registDate;
	//수정자
	private String modifier;
	//수정일자
	private String modifyDate;
	
	private ExerciseDbDTO exerciseDbDTO;

}
