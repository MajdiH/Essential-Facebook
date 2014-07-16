package com.mh.exception.helper;

import java.util.ArrayList;
import java.util.List;

public class ErrorFormInfo {
		
	private String url;
	private String message;
	private List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();
	
	public ErrorFormInfo() {}
	
	public ErrorFormInfo(String url, String message) {
		this.url = url;
		this.message = message;
	}
	
	public ErrorFormInfo(List<FieldErrorDTO> fieldErrors, String url, String message) {
		this.fieldErrors = fieldErrors;
		this.url = url;
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<FieldErrorDTO> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}
