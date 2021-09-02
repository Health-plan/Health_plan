package com.health.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.health.service.MemberService;
//import com.health.service.MemberService;



@Controller
public class HealthPlannerController {
	
	
	//private HealthPlannerService healthplannerSevice;
	@Autowired
	private MemberService memberService;
	
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
	
//   @PostMapping(value= "join/newaccount.do")
//	public String createNewAccount(MemberDTO memberDto) {
//		memberService.joinUser(memberDto);
//		return "redirect:/login";
//	}
//	
	@ResponseBody
	@PostMapping(value = "/idCheck.do")
	public int IdCheck(@RequestBody String mbr_id) throws Exception
	{
		System.out.println(mbr_id);

		int count = 0;
		if(mbr_id != null)
		{
			System.out.println(mbr_id+"1123125");
			count = memberService.idCheck(mbr_id);
			System.out.println(mbr_id+"#");
			
		}
		System.out.println(count);
		return count;	
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
