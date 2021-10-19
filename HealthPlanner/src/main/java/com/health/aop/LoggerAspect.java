package com.health.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component 개발자가 직접 정의한 클래스를 빈으로 등록할 때
//@Bean은 개발자가 제어할 수 없는 외부 라이브러리를 빈으로 등록할 때
@Component // 빈(Bean)등록 어노테이션
@Aspect	//AOP 기능을 하는 클래스의 클래스 레벨에 지정하는 어노테이션
public class LoggerAspect {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//advice의 가장 강력한 기능 - 호출 자체를 제어
	@Around("execution(* com.health..controller.*Controller.*(..)) or execution(* com.health..service.*Impl.*(..)) or execution(* com.health..mapper.*Mapper.*(..))")
	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String type = "";
		String name = joinPoint.getSignature().getDeclaringTypeName();
		
		if(name.contains("Controller") == true) {
			type = "Controller ===> ";
		} else if(name.contains("Service") == true) {
			type = "ServiceImpl ===> ";
		} else if(name.contains("Mapper") == true) {
			type = "Mapper ===> ";
		}
		
		logger.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}
}
