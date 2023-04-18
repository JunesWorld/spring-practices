package com.junesworld.helloweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class HelloController {

	// Tomcat Servlet에서 실행 
	@RequestMapping("/hello")
	public String hello() {
		return "/WEB-INF/views/hello.jsp"; 
	}
}
