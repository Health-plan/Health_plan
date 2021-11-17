package com.health.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoalDTO {
	//아이디
	private String mbrId;
	//목표체중
	private int goalWeight;
	//목표체지방률
	private double goalFatper;
	//목표날짜
	private String goalDate;
	//작성자
	private String registrant;
	//작성일자
	private String registDate;
	//수정자
	private String modifier;
	//수정일자
	private String modifyDate;
}
