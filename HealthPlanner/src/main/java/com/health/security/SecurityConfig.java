package com.health.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.health.security.service.MemberService2;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity //spring security 설정할 클래스라고 정의
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 만듦

//일반적으로 WebSecurityConfigurerAdapter 상속해서 사용
//WebSecurityConfigurer 인스턴스를 편리하게 생성하기 위한 클래스
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private MemberService2 memberService; //로그인 처리, 인증을 위해서는 UserDetailService를 통해서 필요한 정보들을 가져오는데 여기서는 memberService에서 처리

	//Bean 등록
	
	@Bean // Service에서 비밀번호를 암호화 할 수 있도록
	public PasswordEncoder passwordEncoder() { //BCryptPasswordEncoder는 Spring security에서 제공하는 비밀번호 암호화 객체
		return new BCryptPasswordEncoder();
	}
	
	//Security 설정
	
	@Override //필터 설정
	public void configure(WebSecurity web) throws Exception{ // WebSecurity는 FilterChainProxy를 생성하는 필터
		// static 디렉터리의 하위 파일 목록은 인중 무시( = 항상 통과 )
		web.ignoring().antMatchers("/css/**","/js/**","/img/**","/lib/**"); //파일 기준 : resource/static 디렉터리
	//	이렇게 사용할 수도 있음
	//	web.ignoring().requestMatchers(PathRequest.toStaticResource().atCommonLocations()); - 정적자원에 대해서는 적용 안 함
	}
	
	@Override // 웹 기반 보안 구성
	protected void configure(HttpSecurity http) throws Exception { // HttpSecurity를 통해 HTTP 요청에 대한 웹 기반 보안을 구성
		
		http.authorizeRequests() 								// authorizeRequest() - HttpServletRequest에 따라 접근(access)을 제한
			// 페이지 권한 설정
			//.antMatchers("/").hasAnyRole("ADMIN","MEMEBER")
			.antMatchers("/admin/**").hasRole("ADMIN") 			// antMatchers() - 특정 경로 지정
			.antMatchers("/user/myinfo").hasRole("MEMBER")		// permitAll(), hasRole(), hasAnyRole()(여러개 사용 시) - 역할에 따른 접근 설정
			.antMatchers("/**").permitAll()
		.and() // 로그인 설정
			.formLogin()										// form 기반으로 인증. 로그인 정보는 기본적으로 HttpSession을 이용 ('/login' - Spring Security 제공 로그인 form)
			.loginPage("/user/login")							// .loginPage() - 커스텀 로그인 폼을 사용할 때 (***form의 action 경로와 loginPage()의 파라미터 경로가 일치해야 인증처리 가능)
			.defaultSuccessUrl("/user/login/result")			// .defaultSuccessUrl() - 로그인이 성공했을 때 이동되는 페이지 (***컨트롤러 URL에 매핑되어 있어야 함)
			.permitAll()
			//.usernameParameter("파라미터명")						// .usernameParameter() - 해당 메서드 사용 시 input name 파라미터명 변경 가능 (default 설정은 name = username인 input을 기본으로 인식)
		.and() // 로그아웃 설정
			.logout()											// .logout() - 로그아웃을 지원하는 메서드, WebSecurityConfigurerAdapter를 사용할 때 자동으로 적용됨(기능 : HTTP 세션 제거)
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // .logoutRequestMatcher(new AntPathRequestMatcher()) - 로그아웃 URL(default : '/logout') URL 변경할 시
			.logoutSuccessUrl("/user/logout/result")
			.invalidateHttpSession(true)						// .invalidateHttpSession(true) - HTTP 세션 초기화
			//.deleteCookies("KEY명")							// 로그아웃 시, 특정 쿠키를 제거하는 메서드
		.and() // 403 예외처리 핸들링
			.exceptionHandling().accessDeniedPage("/user/denied"); // .exceptionHandling() - 예외가 발생했을 때 핸들링하는 메서드
	}
	
	//
	
	@Override // 비밀번호 암호화
	//Spring Security에서 모든 인증은 AuthenticationManager를 통해 이루어지는데 AuthenticationManger를 생성하기 위해서는 AuthenticationManageBuilder를 사용
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder()); //로그인 처리, 인증을 위해서 필요한 정보를 가져오는 Service에서
																				   //passwordEncoder() - 비밀번호 암호화
	}
	
}
