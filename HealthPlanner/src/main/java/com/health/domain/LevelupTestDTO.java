package com.health.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class LevelupTestDTO extends CommonDTO{
	//신청일시
	private String testDate;
	//아이디
	private String mbrId;
	//운동아이디
	private int exerciseId;
	//평가결과
	private int evaluationResult;
	//평가자
	private String appraiser;
	//실행무게
	private int runningWeight;
	//실행횟수
	private int runningTime;
	//영상경로
	private String videoUrl;
	//등록자
	private String registrant;
	//수정자
	private String modifier;
	//수정일시
	private String modifyDate;
	
	private ExerciseDbDTO exerciseDbDTO;
	private LevelPointDTO levelPointDTO;
	
}
