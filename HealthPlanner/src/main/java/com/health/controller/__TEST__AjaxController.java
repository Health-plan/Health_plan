package com.health.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.health.domain.MessageDTO;

//Ajax 테스트 클래스
@Controller
public class __TEST__AjaxController {
	
	@GetMapping(value = "dataSend.do")
	public String dataSend(Model model) {
		
		return "__TEST_ajax";
	}
	
    @PostMapping(value = "dataSend.do")
    public String dataSend(Model model, MessageDTO dto){
        model.addAttribute("msg",dto.getResult()+"/ this is the value sent by the server ");
        return "__TEST_ajax :: #resultDiv";
    }
}
