package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.health.domain.MbrPointRecordDTO;
import com.health.domain.MemberDTO;
import com.health.domain.PointPoliceDTO;
import com.health.mapper.Admin_PointMapper;
import com.health.service.Admin_PointService;

//Admin_Point Page
@Controller
public class Admin_PointController {
	
	@Autowired
	private Admin_PointService adminPointService;
	
	//List
	@GetMapping(value="admin_point.do")
	public String adminPoint(@ModelAttribute("params") MbrPointRecordDTO params, Model model) {
		List<MbrPointRecordDTO> list = adminPointService.getPointList(params);
		model.addAttribute("list",list);

		return "Admin_Point";
	}
	
	@Autowired
	private Admin_PointMapper adminPointMapper;
	
	@GetMapping(value="admin_pointDetail.do")
	public String adminPointUpdate(@RequestParam(value="mbrId", required=false)String mbrId,
									@RequestParam(value="mbrNm", required=false)String mbrNm,
									@ModelAttribute("params")MbrPointRecordDTO params, Model model) {		
		params.setMbrId(mbrId);
		MemberDTO member = new MemberDTO();
		member.setMbrNm(mbrNm);
		params.setMemberDTO(member);
		System.out.println("이거 객체 테스트" + params.getMemberDTO());
		System.out.println("여기는 mbrNm 넣은 후" + params.getMemberDTO().getMbrNm());
		
		List<MbrPointRecordDTO> list = adminPointService.getPointDetailList(params);
		
		model.addAttribute("mbrId",mbrId);
		model.addAttribute("mbrNm",mbrNm);
		model.addAttribute("list",list);
		
		int totalPoint = adminPointMapper.selectPointTotal(params);
		model.addAttribute("point",totalPoint);
		
		return "Admin_PointDetail";
	}
	
	@GetMapping(value="admin_pointPolice.do")
	public String adminPointPolice(@ModelAttribute("params")PointPoliceDTO params, Model model) {
		List<PointPoliceDTO> police = adminPointService.getPointPoliceList(params);
		model.addAttribute("police",police);
		
		return "Admin_PointPolice";
	}
}
