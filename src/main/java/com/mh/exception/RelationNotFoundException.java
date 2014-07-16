package com.mh.exception;

public class RelationNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -2859292084648724403L;
	private final int relationId;
	
	public RelationNotFoundException(int id) {
		relationId = id;
	}
	
	public int getRelationId() {
		return relationId;
	}

}
