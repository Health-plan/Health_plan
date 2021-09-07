package com.health.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.health.domain.MemberDTO;
import com.health.service.MemberService;


@Controller
public class HealthPlannerController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	//private HealthPlannerService healthplannerSevice;
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping(value = "main.do")
	public String openHealthPlannerMain(Model model) {
		return "main";
	}
	
	@GetMapping(value = "login.do")
	public String openHealthPlannerLogin(Model model) {

		return "login.html";
	}
	

	@PostMapping(value= "login.do")
	public String postHealthPlannerLogin(String mbr_id) throws Exception
    {
		log.debug(mbr_id+"/로그인아이디/로그인버튼");
		
			
		
		return "redirect:/main.do";
	}
	
//    // 로그인 결과 페이지
//    @GetMapping("/login/result")
//    public String dispLoginResult() {
//        return "/loginSuccess";
//    }
//
//    // 로그아웃 결과 페이지
//    @GetMapping("/logout/result")
//    public String dispLogout() {
//        return "/logout";
//    }
//
//    // 접근 거부 페이지
//    @GetMapping("/denied")
//    public String dispDenied() {
//        return "/denied";
//    }
	
	@GetMapping(value = "join.do")
	public String openHealthPlannerJoin(Model model) {

		return "join";
	}
	
	
	@PostMapping(value= "join.do")
	public String createNewAccount( MemberDTO memberDto) throws Exception
    {
		log.debug(memberDto+"/가입버튼");
		memberService.memberRegister(memberDto);
		return "redirect:/login.do";
	}
	

	@ResponseBody
	@PostMapping(value = "/idCheck.do")
	public int IdCheck(@RequestBody String mbr_id) throws Exception
	{
		log.debug(mbr_id);

		int count = 0;
		if(mbr_id != null)
		{
			log.debug(mbr_id+"1123125");
			count = memberService.idCheck(mbr_id);
			log.debug(mbr_id+"#");
			
		}
		log.debug("count : "+count);
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
