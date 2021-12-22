package com.health.security.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto2 {
	
	private Long id;
	private String email;
	private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    
    public MemberEntity toEntity() {
    	return MemberEntity.builder()
    			.id(id)
    			.email(email)
    			.password(password)
    			.build();
    }
    
    @Builder
    public MemberDto2(Long id, String email, String password) {
    	this.id = id;
    	this.email = email;
    	this.password = password;
    }
	

}
