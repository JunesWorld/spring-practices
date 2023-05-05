package com.junesworld.fileupload.service;

import java.util.Calendar;

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
		
		String extName = originalFilename.substring(originalFilename.lastIndexOf('.')+1);
		String restoreFilename = generateSaveFilename(extName);
		
		Long fileSize = multipartFile.getSize();
		
		System.out.println("###### + originalFilename");
		System.out.println("###### + restoreFilename");
		System.out.println("###### + fileSize");
		
		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}

}
