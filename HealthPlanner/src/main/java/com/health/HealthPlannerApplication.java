package com.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class HealthPlannerApplication {
	
	//main메서드는 SpringApplication.run 메서드를 호출해서 웹 애플리케이션을 실행하는 역할
	public static void main(String[] args) {
		SpringApplication.run(HealthPlannerApplication.class, args);
	}

}
