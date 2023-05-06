package com.junesworld.fileupload.exception;

public class FileUploadServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public FileUploadServiceException() {
		super("File Upload Service Exception");
	}
	
	public FileUploadServiceException(String message) {
		super(message);
	}
}
