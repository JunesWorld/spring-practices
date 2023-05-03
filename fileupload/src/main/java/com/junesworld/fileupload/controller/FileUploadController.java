package com.junesworld.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	
	@RequestMapping("/form")
	public String form() {
		return "/WEB-INF/views/form.jsp";
	}
	
	@RequestMapping("/upload")
	public String upload(
			@RequestParam("email" ) String eamil, 
			// multipartFile = form.jsp에 있는 이름으로 requestparam에 넣어주기  
			@RequestParam("file" ) MultipartFile multipartFile) {
		return "/WEB-INF/views/result.jsp";
	}

}
