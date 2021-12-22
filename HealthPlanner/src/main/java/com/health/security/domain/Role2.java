package com.health.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role2 {
	//Service에서 사용하는 Enum객체
	ADMIN("ROLE_ADMIN"),
	MEMBER("ROLE_MEMBER");
	
	private String value;
}
