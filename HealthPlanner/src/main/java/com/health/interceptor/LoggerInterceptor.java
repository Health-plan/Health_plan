package com.health.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{

	//preHandle - 컨트롤러의 메서드에 매핑된 특정 URI를 호출했을 때 컨트롤러에 접근하기 전에 실행되는 메소드
				//사용자가 화면에서 어떠한 기능을 수행했을 때 해당 기능과 매핑된 URI 정보를 쉽게 파악할 수 있도록 콘솔에 로그를 출력하도록 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub	
		//log.debug("================= BEGIN ==============");
		//log.debug("Request URI ===> " + request.getRequestURI());
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	//postHandle - 컨트롤러를 경유한 다음, 화면(View)으로 결과를 전달하기 전에 실행되는 메서드
				//요청의 끝을 알리는 로그를 콘솔에 출력하도록 처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		//log.debug("================= END ==============");
	}

}
