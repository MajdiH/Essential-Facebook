package com.mh.exception;

public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -2859292084648724403L;
	private final int userId;
	
	public UserNotFoundException(int id) {
		userId = id;
	}
	
	public int getUserId() {
		return userId;
	}

}
