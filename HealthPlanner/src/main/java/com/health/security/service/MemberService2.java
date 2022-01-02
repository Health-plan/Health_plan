package com.health.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.health.security.domain.MemberDto2;
import com.health.security.domain.MemberEntity;
import com.health.security.domain.MemberRepository;
import com.health.security.domain.Role2;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService2 implements UserDetailsService{
	private MemberRepository memberRepository;
	
	//@Transcational
	//회원가입을 처리하는 메서드
	public Long joinUser(MemberDto2 memberDto) { 
		//비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword())); // 비밀번호 암호화하여 저장
		
		return memberRepository.save(memberDto.toEntity()).getId();
	}
	
	@Override
	//상세정보 조회 메서드(매개변수 : 로그인 시 입력한 아이디)
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(userEmail); // 엔티티는 PK가 아니라 유저를 식별할 수 있는 어떤 값(username)
		MemberEntity userEntity = userEntityWrapper.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		//예시
		if(("admin@example.com").equals(userEmail)) {
			authorities.add(new SimpleGrantedAuthority(Role2.ADMIN.getValue()));	//authorities.add(new SimpleGrantedAuthority()) - 롤을 부여하는 코드 (회원가입 시 부여하면 좋다)
		} else {
			authorities.add(new SimpleGrantedAuthority(Role2.MEMBER.getValue()));
		}
		
		return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);  //new User() - SpringSecurity에서 제공하는 UserDetails를 구현한 User를 반환
																						//예시에서는 아이디, 비밀번호, 권한리스트 순으로 진행
																						//사용자의 계정정보와 권한을 갖는 UserDetails 인터페이스를 반환
	}
}
