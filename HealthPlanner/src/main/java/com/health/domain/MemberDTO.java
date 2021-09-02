package com.health.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MemberDTO {
	
	
	private String mbrId;
	private String mbrPw;
	private String mbrNm;
	private LocalDateTime mbrBirth;
	private String mbrEmail;
	private String mbrPwHint;
	private String mbrPwAnswer;
	private String mbrPhoto;
	private int goalRegister;
	private LocalDateTime mbrJoin;
	private int managementRank;
	private int secession;
	private String registrant;
	private LocalDateTime registDate;
	private String modifier;
	private LocalDateTime modiyDate;
	
}
