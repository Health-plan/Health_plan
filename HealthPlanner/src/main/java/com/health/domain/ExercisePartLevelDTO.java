package com.health.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExercisePartLevelDTO {
	//몸무게
	private int levelId;
	//레벨아이디
	private int exerciseId;
	//운동아이디
	private int exerciseWeight;
	//무게
	private int exerciseTime;
	//횟수
	private int bodyWeight;
	//성별
	private int gender;
	//등록자
	private String registrant;
	//등록일시
	private String registDate;
	//수정자
	private String modifier;
	//수정일시
	private String modifyDate;

}
