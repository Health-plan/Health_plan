package com.health.controller;

import java.util.List;

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
import com.health.domain.PointDTO;
import com.health.domain.UserDTO;
import com.health.service.MemberService;

//import oracle.jdbc.proxy.annotation.Post;


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
		System.out.println("session값NOTnull? " + loginInfo);
		return "main";
	}
	
	@GetMapping(value = "login.do")
	public String openHealthPlannerLogin(Model model) {

		return "login";
	}
	
	

	
	@PostMapping(value= "login.do")
	public String ReadAccount( MemberDTO userdto, HttpServletRequest req, RedirectAttributes rttr) throws Exception
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
	
	//마이페이지 - 비밀번호확인
		@GetMapping(value = "mypage.do")
		public String openMypagePass(HttpSession session, Model model) throws Exception
		{
			Object mypageSessionNull =session.getAttribute("member");
			System.out.println("세션값 동일? "+mypageSessionNull);
			if(mypageSessionNull != null)
			{
				return "mypage_pass";
			}
			
			else 
			{
				return "redirect:/main.do";
			}
	
		}
		
		@GetMapping(value = "mypage_fail.do")
		public String openHealthPlannerMypagePassFail(Model model)
		{
			return "mypage_pass";
		}
		
		@PostMapping(value = "mypage.do")
		public String mypagePwCheck(HttpSession session,  MemberDTO mbrdto, Model model) throws Exception
		{
			
			MemberDTO myloginSessions =  (MemberDTO)session.getAttribute("member");
			
			System.out.println("들어오는 pw"+myloginSessions);
			
			String SessionId = myloginSessions.getMbrId();
			String SessionPw = myloginSessions.getMbrPw();
			String SessionNm = myloginSessions.getMbrNm();
			String SessionEm = myloginSessions.getMbrEmail();
			String SessionPt = myloginSessions.getMbrPhoto();
			
			mbrdto.setMbrId(SessionId);
			 MemberDTO pwCheck = memberService.mypagePasswordCheck(mbrdto);
			
			System.out.println("마이페이지세션"+ myloginSessions);
			
			
			if( myloginSessions ==null || pwCheck == null)
			{
				System.out.println("세션0일때"+mbrdto +"///\n" +pwCheck);
				
				return "redirect:/mypage_fail.do";							
			}
			else
			{
				 
				System.out.println("session 0아닐때"+mbrdto+"///" +pwCheck);
				System.out.println("session정보"+SessionEm+"//\\" +SessionPt);
				System.out.println("세션아이디"+SessionId+"/타입" +SessionId.getClass().getName());			
				model.addAttribute("userId",SessionId);
				model.addAttribute("userPw",SessionPw);
				model.addAttribute("userNm",SessionNm);
				model.addAttribute("userEm",SessionEm);
				model.addAttribute("userPhoto", SessionPt);
				return "mypage_main";							
			}
			
		}
		
		@PostMapping(value = "mypage_pwchange.do")
		public String mypagePwChange(HttpSession session,  MemberDTO mbrdto, Model model) throws Exception
		{
			
			MemberDTO myloginSessions =  (MemberDTO)session.getAttribute("member");
			
			memberService.changeNewPwInMyPage(mbrdto);
		
			
			System.out.println("마이페이지세션"+ myloginSessions);
			
			
			if( myloginSessions ==null)
			{
				System.out.println("세션0일때"+mbrdto );
				
				return "redirect:/main.do";							
			}
			else
			{
				 
				System.out.println("session 0아닐때"+mbrdto);
		
				return "redirect:/mypage.do";							
			}
			
		}
		
		//마이페이지 - 비밀번호확인
				@GetMapping(value = "mypage_graph.do")
				public String openMypage_graph(HttpSession session, Model model) throws Exception
				{
					MemberDTO myloginSessions =  (MemberDTO)session.getAttribute("member");
					
					System.out.println("세션값 동일? "+myloginSessions);
					if(myloginSessions != null)
					{
						model.addAttribute("userNm",myloginSessions.getMbrNm());
						
						return "mypage_graph";
					}
					
					else 
					{
						return "redirect:/main.do";
					}
			
				}
				
				@GetMapping(value = "mypage_body.do")
				public String openMypage_body(HttpSession session, Model model) throws Exception
				{
					MemberDTO myloginSessions =  (MemberDTO)session.getAttribute("member");
					System.out.println("세션값 동일? "+myloginSessions);
					if(myloginSessions != null)
					{
						model.addAttribute("userNm",myloginSessions.getMbrNm());
						return "mypage_body";
					}
					
					else 
					{
						return "redirect:/main.do";
					}
			
				}
				
			
				
				@GetMapping(value = "mypage_point.do")
				public String openMypage_point(HttpSession session, Model model) throws Exception
				{
					MemberDTO myloginSessions =  (MemberDTO)session.getAttribute("member");
					List<PointDTO> pointList = memberService.pointContentsList(myloginSessions);
					int pointTotal= memberService.pointValueTotal(myloginSessions);
					
					if(myloginSessions != null)
					{
						//model.addAttribute("pointValue",myloginSessions.getPointValue());
						model.addAttribute("userNm",myloginSessions.getMbrNm());
						model.addAttribute("pointList", pointList);
						model.addAttribute("pointTotal", pointTotal);
						
						System.out.println("포인트 리스트 들어오는지?? "+pointList);
						return "mypage_point";
					}
					
					else 
					{
						return "redirect:/main.do";
					}
			
				}
		
}
