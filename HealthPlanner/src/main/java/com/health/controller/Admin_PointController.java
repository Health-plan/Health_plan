package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.health.domain.MbrPointRecordDTO;
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
		System.out.println(list.size());
		for( int i=0; i<list.size(); i++ ) {
			System.out.println(list.get(i).getPointPoliceDTO().getPointValue());
		}
		
		return "Admin_Point";
	}
	
	@GetMapping(value="admin_pointUpdate.do")
	public String adminPointUpdate(Model model) {
		
		return "Admin_PointUpdate";
	}
}
