package com.health.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminMemberProgressVO {

	private MemberStateDTO start;
	
	private MemberStateDTO progress;
	
	private GoalDTO goal;
	
}
