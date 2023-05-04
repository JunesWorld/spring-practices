package com.junesworld.fileupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.junesworld.fileupload.service.FileUploadService;

@Controller
public class FileUploadController {
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("/form")
	public String form() {
		return "/WEB-INF/views/form.jsp";
	}
	
	@RequestMapping("/upload")
	public String upload(
			@RequestParam("email" ) String email, 
			// multipartFile = form.jsp에 있는 이름으로 requestparam에 넣어주기  
			@RequestParam("file" ) MultipartFile multipartFile,
			Model model) {
		
		System.out.println("email:" + email);
		
		String url = fileUploadService.restore(multipartFile);
		
		model.addAttribute("url", url);
		return "/WEB-INF/views/result.jsp";
	}

}
