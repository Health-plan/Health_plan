package com.health.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.health.domain.LevelupTestDTO;
import com.health.service.Admin_LevelupTest;

@Controller
public class Admin_levelupController {
	
	@Autowired
	private Admin_LevelupTest adminLevelupTest;
	
	@GetMapping(value="admin_levelup.do")
	public String adminLevelUp(@ModelAttribute(value="params")LevelupTestDTO params, Model model) {
		
		List<LevelupTestDTO> levelup = adminLevelupTest.getTestList(params);
		model.addAttribute("levelup",levelup);
		
		return "Admin_Levelup";
	}
	
	@GetMapping(value="admin_levelupDetail.do")
	public String adminLevelUpDetail(@ModelAttribute(value="params")LevelupTestDTO params,
								@RequestParam(value="testDate", required=false) String testDate,
								@RequestParam(value="mbrId",required=false)String mbrId,
								@RequestParam(value="exerciseId", required=false)int exerciseId, Model model) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("testDate", testDate);
			map.put("mbrId", mbrId);
			map.put("exerciseId", exerciseId);
			
			System.out.println("여기는 처음 params : "+params);
			
			if(testDate == null || mbrId == null || exerciseId == 0) {
				return "redirect:admin_levelup.do";
			}
			
			LevelupTestDTO levelup = adminLevelupTest.getTestDetail(map);
			model.addAttribute("levelup",levelup);
			
			
			return "Admin_LevelupDetail";	
	}
	
	@PostMapping(value="admin_levelupUpdate.do")
	public String levelupUpdate(@ModelAttribute(value="params")LevelupTestDTO params,
			@RequestParam(value="testDate", required=false) String testDate,
			@RequestParam(value="mbrId",required=false)String mbrId,
			@RequestParam(value="exerciseId", required=false)int exerciseId, Model model) {
		if(testDate == null || mbrId == null || exerciseId == 0) {
			return "redirect:admin_levelup.do";
		}
		try {
			boolean isUpdate = adminLevelupTest.confirmTest(params);
			if(isUpdate == false) {
				System.out.println("메소드문제");
			}
			
		} catch(DataAccessException e) {
			System.out.println("디비 에러 : " + e);
		}
		catch(Exception e) {
			System.out.println("시스템 에러 : " + e);
		}
		
		return "redirect:admin_levelup.do";
	}

}
