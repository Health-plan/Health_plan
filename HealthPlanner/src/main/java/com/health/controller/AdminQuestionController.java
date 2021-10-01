package com.health.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.health.domain.PostDTO;
import com.health.service.AdminQuestionService;

@Controller
public class AdminQuestionController {

	@Autowired
	private AdminQuestionService adminQuestionService;

	// 게시판 GET
	@GetMapping(value="getBoard.do")
	public String getBoard(@ModelAttribute("params") PostDTO postDto, Model model) {
		System.out.println("GetMapping getBoard.do 요청할 때 넘어온 params : " + postDto);
		
		List<PostDTO> postList = adminQuestionService.getPostList(postDto);
		model.addAttribute("postList",postList);
		
		return "__TEST_GetBoard";
	}
		
	//게시판 상세
	@GetMapping(value="BoardDetail.do")
	public String getBoardDetail(@RequestParam(value="postId", required = false)int postId, Model model) {
		if(postId == 0) {
			//TODO => 올바르지 않은 접근이라는 메시지를 전달, 게시글 리스트로 리다이렉트
			return "redirect:getBoard.do";
		}
		PostDTO post = adminQuestionService.getPostDetail(postId);
		if(post == null || post.getAvailable() == 1) {
			//TODO => 없는 게시글이거나, 삭제된 게시글이라는 메시지를 전달, 리스트로 리다이렉트
			return "redirect:getBoard.do";
		}
		model.addAttribute("post",post);

		return "__TEST_BoardDetail";
	}
	
	//게시판 상세 답변창 노출
	@PostMapping(value="BoardDetailWrite.do")
	public String getBoardDetailWrite(@RequestParam(value="postId", required = false)int postId, int writeContain, Model model) {
		//상세정보
		PostDTO post = adminQuestionService.getPostDetail(postId);
		model.addAttribute("post",post);
		
		//답변창 기능에 사용할 변수 정의
		model.addAttribute("writeContain",writeContain);
		System.out.println("값은" + writeContain);
		
		return "__TEST_BoardDetail :: #writeAnswer";
	}
	
	//게시판 답변 수정&등록
	@PostMapping(value="BoardDetailAnswer.do")
	public String getBoardDetailAnswer(@RequestParam(value="postId", required = false)int postId, String answer, int writeContain, Model model) {
		model.addAttribute("writeContain",writeContain);
		
		PostDTO post = adminQuestionService.getPostDetail(postId);
		//답변 내용 저장
		post.setAnswer(answer);
		//답변 기능 메소드
		adminQuestionService.adminQuestionAnswer(post);
		//DB에서 바뀐 Detail값을 다시 불러옴
		post = adminQuestionService.getPostDetail(postId);
		//template로 넘김
		model.addAttribute("post",post);
				
		return "__TEST_BoardDetail :: #writeAnswer";
	}
	
	
	
	
	
	
	
	
	
	
	// 게시판 POST
	// 게시판 조회(Get)
	@GetMapping(value="postBoard.do")
	public String testpage2(@ModelAttribute("params") PostDTO postDto, Model model) {
		
		System.out.println("getmapping testList.do 요청할 때 넘어온 params : " + postDto);
		
		List<PostDTO> postList = adminQuestionService.getPostList(postDto);
		model.addAttribute("postList",postList);
		
		return "__TEST_PostBoard";
	}
	
	// 갱신(Post)
	@PostMapping(value="postSort.do")
	public String sort2(@ModelAttribute("params") PostDTO postDto, Model model) {
		
		System.out.println("postmapping sort.do 요청할 떄 넘어온 params : " + postDto);
		
		List<PostDTO> postList = adminQuestionService.getPostList(postDto);
		model.addAttribute("postList",postList);
		
		return "__TEST_PostBoard :: #listTest";
	}

}
