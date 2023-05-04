package com.junesworld.fileupload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	public String restore(MultipartFile multipartFile) {
		String url = null;
		
		if(multipartFile.isEmpty()) {
			return url;
		}
		
		String originalFilename = multipartFile.getOriginalFilename();
		Long fileSize = multipartFile.getSize();
		
		System.out.println("###### + originalFilename");
		System.out.println("###### + fileSize");
		
		return url;
	}

}
