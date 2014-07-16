package com.mh.exception;

public class MessageNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -2859292084648724403L;
	private final int messageId;
	
	public MessageNotFoundException(int id) {
		messageId = id;
	}
	
	public int getMessageId() {
		return messageId;
	}

}
