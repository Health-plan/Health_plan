package com.health.domain;

import com.health.paging.Criteria;
import com.health.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO extends Criteria{

	private PaginationInfo paginationInfo;
	
	private int available;
	
	private String postDate;
	
	private String modfiyDate;
}
