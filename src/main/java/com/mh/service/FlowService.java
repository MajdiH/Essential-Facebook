package com.mh.service;

import java.util.List;

import com.mh.exception.FlowNotFoundException;
import com.mh.model.Flow;

public interface FlowService {
	public Flow create(Flow flow);
	public Flow get(Integer id) throws FlowNotFoundException;
	public List<Flow> getAll();
	public Flow update(Flow flow);
	public Flow delete(Integer id);
	public Flow like(Integer id);
}
