package com.junesworld.helloweb.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public String hello2(String name, Long no) {
		System.out.println("name:" + name + ", no:" + no); 
		return "/WEB-INF/views/hello.jsp"; 
	}
	
	@RequestMapping("/hello3")
	public ModelAndView hello3(String name) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);
		mav.setViewName("/WEB-INF/views/hello3.jsp");
		return mav;
	}
	
	@RequestMapping("/hello4")  // 추천 !! 
	public String hello4(String name, Model model) {
		model.addAttribute("name", name);
		return "/WEB-INF/views/hello4.jsp";
	}
	
	@ResponseBody  // 바로 return -> API 통신할 때 사용 
	// localhost:8080/helloweb/hello5 -> Hello World 
	@RequestMapping("/hello5")
	public String hello5() {
		return "<h1>Hello World</h1>";
	}
	
	@RequestMapping("/hello6")  
	// 내가 준 URL만 사용하면 됨 
	// helloweb/hello6 -> hello로 리다이렉션 
	public String hello6() {
		return "redirect:/hello";
	}
	
	@RequestMapping("/hello7")  
	// 비추, 안좋은 코드 (기술이 침투되어 있다) 
	public void hello6(
		HttpServletRequest request,
		HttpServletResponse response,
		Writer out) throws IOException {
		
		String name = request.getParameter("name");
		// response.getWriter().println("hello " + name);  // Writer out과 아래줄 제거
		out.write("hello " + name);
	}
}
