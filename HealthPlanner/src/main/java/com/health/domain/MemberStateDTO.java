package com.health.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberStateDTO {

	/** 일자 (PK) */
	private String memberStateDate;

	/** 아이디(FK) */
	private String mbrId;

	/** 성별 */
	private String mbrGender;

	/** 나이 */
	private int mbrAge;

	/** 키 */
	private int mbrHeight;

	/** 몸무게 */
	private int mbrWeight;

	/** 체지방률 */
	private int memberFatper;

	/** 종합 신체 등급 */
	private String averageRank;

	/** 목표달성률 */
	private int goalPer;

	/** 등록자 */
	private String registrant;

	/** 등록일자 */
	private String registDate;
	
	/** 수정자 */
	private String modifier;
	
	/** 수정일자 */
	private String modifyDate;

	@Override
	public String toString() {
		return "MemberStateDTO [memberStateDate=" + memberStateDate + ", mbrId=" + mbrId + ", mbrGender=" + mbrGender
				+ ", mbrAge=" + mbrAge + ", mbrHeight=" + mbrHeight + ", mbrWeight=" + mbrWeight + ", memberFatper="
				+ memberFatper + ", averageRank=" + averageRank + ", goalPer=" + goalPer + ", registrant=" + registrant
				+ ", registDate=" + registDate + ", modifier=" + modifier + ", modifyDate=" + modifyDate + "]";
	}
	
	

}