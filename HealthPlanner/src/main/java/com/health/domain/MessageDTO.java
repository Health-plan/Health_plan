package com.health.domain;

import lombok.Data;

@Data
public class MessageDTO {
	private String msg;
	private String Result;
	
	public String message;
	public String href;
	
	public MessageDTO(String message, String href) {
		this.message = message;
		this.href = href;
	}
}
