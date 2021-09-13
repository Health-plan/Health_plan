package com.health.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	//아이디
		private String mbrId;
		//비밀번호
		private String mbrPw;
		
		private String ManagementRank;
}
