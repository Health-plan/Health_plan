package com.health.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	///회원가입---
	//아이디
	private String mbrId;
	//비밀번호
	private String mbrPw;
	//이름
	private String mbrNm;
	//생년월일
	private String mbrBirth;
	//이메일
	private String mbrEmail;
	//비밀번호힌트
	private String mbrPwHint;
	//비밀번호힌트답
	private String mbrPwAnswer;
	//프로필사진
	private String mbrPhoto;
	//목표등록여부
	private int goalRegister;

	//관리등급
	private String managementRank;
	//탈퇴여부
	private int secession;
	//등록자
	private String registrant;
	//등록일자
	private String registDate;
	//수정자
	private String modifier;
	//수정일자
	private String modifyDate;

	////mbr_point_record
	private int pointId;
	private String pointKind;
	//// point_police
	private int pointValue;
	private String pointContents;
	private String pointCategory;
	
	@Override
	public String toString() {
		return "MemberDTO [mbrId=" + mbrId + ", mbrPw=" + mbrPw + ", mbrNm=" + mbrNm + ", mbrBirth=" + mbrBirth
				+ ", mbrEmail=" + mbrEmail + ", mbrPwHint=" + mbrPwHint + ", mbrPwAnswer=" + mbrPwAnswer + ", mbrPhoto="
				+ mbrPhoto + ", goalRegister=" + goalRegister + ", managementRank=" + managementRank + ", secession="
				+ secession + ", registrant=" + registrant + ", registDate=" + registDate + ", modifier=" + modifier
				+ ", modifyDate=" + modifyDate + ", pointId=" + pointId + ", pointKind=" + pointKind + ", pointValue="
				+ pointValue + ", pointContents=" + pointContents + ", pointCategory=" + pointCategory + "]";
	}
	
	
	
	

	
	
}
