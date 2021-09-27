package com.health.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.health.domain.PostDTO;
import com.health.domain.TestDTO;

@RestController
@Controller
public class TestController {

	@RequestMapping("test")
	public TestDTO test() {
		
		TestDTO test = new TestDTO();
		
		test.setTitle("RestController");
		test.setContent("RestController contents");
		
		return test;
	}

	@ResponseBody
	@RequestMapping("test2")
		public TestDTO test2() {
		
		TestDTO test = new TestDTO();
		
		test.setTitle("Controller");
		test.setContent("ResponseBody");
		
		return test;
	}
	
	@GetMapping(value="test.do")
	public String test(PostDTO test,Model model) {
		model.addAttribute("test",test);
		test.setMbrId("test");
		
		return "test";
	}
}