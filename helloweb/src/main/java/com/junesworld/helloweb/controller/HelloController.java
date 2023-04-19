package com.junesworld.helloweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class HelloController {
	public void a() {
		
	}

	// Tomcat Servlet에서 실행 
	@RequestMapping("/hello")
	public String hello() {
		return "/WEB-INF/views/hello.jsp"; 
	}
	
	@RequestMapping("/hello2")
	// localhost8080:helloweb/hello2?name=june&no=10 -> [server] name : june, no : 10
	public String hello(String name, Long no) {
		System.out.println("name:" + name + ", no:" + no); 
		return "/WEB-INF/views/hello.jsp"; 
	}
}
