package com.mh.exception;

public class FlowNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -2859292084648724403L;
	private final int flowId;
	
	public FlowNotFoundException(int id) {
		flowId = id;
	}
	
	public int getFlowId() {
		return flowId;
	}

}
