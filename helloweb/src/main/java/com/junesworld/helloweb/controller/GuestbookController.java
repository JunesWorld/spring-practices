package com.junesworld.helloweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// @RequestMapping 클래스 단독 매핑
// UserController와 같은 방식 추천 
@Controller
@RequestMapping("/guestbook/*")
public class GuestbookController {
	
	@ResponseBody
	@RequestMapping //("URL 있어야하지만 생략) -> 아래 METHOD -> /helloweb/guestbook/list  // 핸들러라는 표시, 매핑은 클래스!!
	public String list() {
		return "GuestbookController.list()";
	}
	
	@ResponseBody
	@RequestMapping 
	public String delete() {
		return "GuestbookController.delete()";
	}
}
