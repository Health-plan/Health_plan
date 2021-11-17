package com.health.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberStateDTO {

	/** 일자 (PK) */
	private String memberStateDate;

	/** 아이디(FK) */
	private String mbrId;
	
	/** 제목 */
	private String title;

	/** 성별 */
	private int mbrGender;

	/** 나이 */
	private int mbrAge;

	/** 키 */
	private int mbrHeight;

	/** 몸무게 */
	private int mbrWeight;

	/** 체지방률 */
	private double memberFatper;

	/** 종합 신체 등급 */
	private String averageRank;

	/** 목표달성률 */
	private int goalPer;

	/** 등록자 */
	private String registrant;
	
	/** 수정자 */
	private String modifier;
	
	/** 수정일자 */
	private String modifyDate;

	@Override
	public String toString() {
		return "MemberStateDTO [memberStateDate=" + memberStateDate + ", mbrId=" + mbrId + ", title=" + title 
				+", mbrGender=" + mbrGender	+ ", mbrAge=" + mbrAge + ", mbrHeight=" + mbrHeight + ", mbrWeight="
				+ mbrWeight + ", memberFatper=" + memberFatper + ", averageRank=" + averageRank + ", goalPer="
				+ goalPer + ", registrant=" + registrant + ", modifier=" + modifier
				+ ", modifyDate=" + modifyDate + "]";
	}
	
	

}