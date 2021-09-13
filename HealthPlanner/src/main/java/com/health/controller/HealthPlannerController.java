package com.health.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.health.domain.MemberDTO;
import com.health.domain.UserDTO;
import com.health.service.MemberService;


@Controller
public class HealthPlannerController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	//private HealthPlannerService healthplannerSevice;
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping(value = "main.do")
	public String openHealthPlannerMain(HttpSession session, Model model) {
		
		Object loginInfo = session.getAttribute("member");
		
		if(loginInfo ==null)
		{
			System.out.println("session값null? " + loginInfo);
			model.addAttribute("nullsession",false);
		}
		
		return "main";
	}
	
	@GetMapping(value = "login.do")
	public String openHealthPlannerLogin(Model model) {

		return "login";
	}
	
	

	
	@PostMapping(value= "login.do")
	public String ReadAccount( UserDTO userdto, HttpServletRequest req, RedirectAttributes rttr) throws Exception
    {
		log.debug(userdto+"/로그인아이디/로그인버튼");
		System.out.println(userdto+"/로그인아이디/로그인버튼");
		
		
		memberService.readAccount(userdto,req,rttr);
		System.out.println(req.getSession().getAttribute("member")+"로그인성공여부");
		if( req.getSession().getAttribute("member")==null)
		{
			return "redirect:/login.do";
		}
		else
		{
			return "redirect:/main.do";
		}
		
	}
	 
	
	@GetMapping(value="/logout")
	public  String logout(HttpSession session) throws Exception
	{	
		session.invalidate();
		return "redirect:/main.do";
	}
	
	@GetMapping(value = "join.do")
	public String openHealthPlannerJoin(Model model) {

		return "join";
	}
	
	
	@PostMapping(value= "join.do")
	public String createNewAccount( MemberDTO memberDto) throws Exception
    {
		log.debug(memberDto+"/가입버튼");
		memberService.memberRegister(memberDto);
		
		return "redirect:/main.do";
	}
	

	@ResponseBody
	@PostMapping(value = "/idCheck.do")
	public int IdCheck(@RequestBody String mbr_id) throws Exception
	{
		log.debug(mbr_id);
		System.out.println(mbr_id);
		
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
