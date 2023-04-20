package com.junesworld.helloweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// RequestMapping 매서드 단독 mapping

@Controller
public class BoardController {
	
	@ResponseBody
	@RequestMapping("/board/write")
	public String write() {
		return " BoardController.write()";
	}
	
	// localhost:8080/helloweb/board/view?no=10 -> 비추 
	// localhost:8080/helloweb/board/view/10 -> 추 
	@ResponseBody
	@RequestMapping("/board/view/{no}")  // 추천 (비추: /board/view?no=10)
	public String view(@PathVariable("no") Long no) {
		return "BoardController.view(" + no + ")";
	}
}
