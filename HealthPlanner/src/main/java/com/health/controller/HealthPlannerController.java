package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class HealthPlannerController {
	
	//@Autowired
	//private HealthPlannerService healthplannerSevice;
	
	@GetMapping(value = "main.do")
	public String openHealthPlannerMain(Model model) {
		return "main";
	}
	
	@GetMapping(value = "login.do")
	public String openHealthPlannerLogin(Model model) {

		return "login";
	}
	
	@GetMapping(value = "join.do")
	public String openHealthPlannerJoin(Model model) {

		return "join";
	}
	@GetMapping(value = "forget_id.do")
	public String openHealthPlannerForgetId(Model model)
	{
		return "forget_id";
	}
	@GetMapping(value = "forget_pw.do")
	public String openHealthPlannerForgetPw(Model model)
	{
		return "forget_pw";
	}
	

}
