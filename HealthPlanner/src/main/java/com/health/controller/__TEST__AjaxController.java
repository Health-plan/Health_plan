package com.health.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.health.domain.MessageDTO;
import com.health.domain.SubMessageDTO;

//Ajax 테스트 클래스
@Controller
public class __TEST__AjaxController {
	
	@GetMapping(value = "dataSend.do")
	public String dataSend(Model model) {
		
		System.out.println(model.getClass().getName());
		

		return "__TEST_ajax";
	}

    @PostMapping(value = "data.do")
    public String dataSend(Model model, Map<String, Object> test, MessageDTO dto, SubMessageDTO dto2){
    	System.out.println(test);
    	System.out.println(test.get("dto"));
    	System.out.println(test.get("dto2"));
    	
    	//현재 model과 dto는 같은 객체에 같은 값을 가지고 있음
    	System.out.println("데이터센드 객체 모델 : " +model);
    	System.out.println("데이터센드 객체 디티오 : " + dto);
    	System.out.println("데이터센드 객체 디티오2 : " + dto2);
    	
    	model.addAttribute("msg",dto.getResult());
    	model.addAttribute("test",dto);
    	model.addAttribute("test2",dto2);
    	
        System.out.println("이건 처음값 : " + dto.getResult());
    	System.out.println("이건 나중값인데 하나만 : " + dto.getTest1());
        
        return "__TEST_ajax :: #resultDiv";
    }
}
