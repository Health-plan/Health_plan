package com.health.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {
	
	// 현재 페이지 번호
	private int currentPageNo;
	// 페이지당 출력할 데이터 개수
	private int recordsPerPage;
	// 화면 하단에 출력할 페이지 사이즈
	private int pageSize;
	// 검색 키워드
//	private String searchKeyword;
	// 정렬 유형
	private int sortType;
	
	public Criteria() {
		this.currentPageNo = 1;
		this.recordsPerPage = 10;
		this.pageSize = 10;
		this.sortType = 2;
	}
	
	public int getStartPage() {
		return (currentPageNo - 1) * recordsPerPage;
	}
	
	public String makeQueryString(int pageNo) {
		//웹페이지 파라미터를 유지시켜주느느 클래스
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("currentPageNo", pageNo)
				.queryParam("recordsPerPage",recordsPerPage)
				.queryParam("pageSize",pageSize)
				.build()
				.encode();
		
		return uriComponents.toUriString();
	}

}
