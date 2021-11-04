package com.health.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Admin_levelupController {
	
	@GetMapping(value="admin_LevelUp.do")
	public String adminLevelUp(Model model) {
			
			return null;
		
	}

}
