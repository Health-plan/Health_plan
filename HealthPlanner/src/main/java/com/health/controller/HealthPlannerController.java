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

import oracle.jdbc.proxy.annotation.Post;


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
	
	@ResponseBody
	@PostMapping(value = "/emailCheck.do")
	public int EmailCheck(@RequestBody String mbr_email) throws Exception
	{
		log.debug(mbr_email);
		System.out.println(mbr_email);
		
		int emailcount = 0;
		if(mbr_email != null)
		{
			log.debug(mbr_email+"1123125");
			emailcount = memberService.emailCheck(mbr_email);
			log.debug(mbr_email+"#");
			
		}
		log.debug("count : "+emailcount);
		return emailcount;	
	}
	

	//아이디 찾기 페이지
	@GetMapping(value = "forget_id.do")
	public String openHealthPlannerForgetId(Model model)
	{		
		return "forget_id";
	}
	//아이디 찾기 (정확한 이메일로 쿼리문이 정상작동했을때 입력한 이메일에 맞는 아이디값을 id라는 모델에 넣는다.)
	@PostMapping(value = "forget_id.do")
	public String ForgetId2(MemberDTO mbrdto, Model model, RedirectAttributes rttr ) throws Exception
	{
		
		MemberDTO finduserid= memberService.findId(mbrdto, rttr);
		if(finduserid != null)
		{
			model.addAttribute("id",finduserid.getMbrId());
			return "forget_id_2";
		}
		
		else {
			return "redirect:/forget_id.do";
		}
		
		
	}
	
	
	//비밀번호 찾기 페이지
	@GetMapping(value = "forget_pw.do")
	public String openHealthPlannerForgetPw(Model model)
	{
		return "forget_pw";
	}
	
	@PostMapping(value = "forget_pw.do")
	public String InputIdForPw(MemberDTO mbrdto, Model model, RedirectAttributes rttr) throws Exception
	{
		
		MemberDTO finduserpwhint= memberService.findPwhint(mbrdto, rttr);
		if(finduserpwhint != null)
		{	model.addAttribute("userId",finduserpwhint.getMbrId());
			model.addAttribute("pwHint",finduserpwhint.getMbrPwHint());
			model.addAttribute("pwAnswer",finduserpwhint.getMbrPwAnswer());
			
			return "forget_pw_2";
		}
		else
		{
			return "redirect:/forget_pw.do";
		}
		
	}
	
	
	
	
	//비밀번호 찾기 페이지2 비밀번호 힌트와 정답
	@PostMapping(value = "forget_pw_2.do")
	public String FindPwHintAnswer(MemberDTO mbrdto, Model model, RedirectAttributes rttr) throws Exception
	{
		
		MemberDTO hiddenparameter=memberService.inputPwHintAnswer(mbrdto, rttr);
		
		if(hiddenparameter != null)
		{	model.addAttribute("userId",hiddenparameter.getMbrId());
			model.addAttribute("pwHint",hiddenparameter.getMbrPwHint());
			model.addAttribute("pwAnswer",hiddenparameter.getMbrPwAnswer());

			
			return "forget_pw_3";
		}
		else {
			return "redirect:/forget_pw.do";
		}
		
		
		
	}
	
	//비밀번호 변경
	@PostMapping(value = "forget_pw_3.do")
	public String ChangePw(MemberDTO mbrdto, Model model) throws Exception
	{
		
		memberService.changeNewPw(mbrdto);
		
		return "login";
		
	}
	
}
