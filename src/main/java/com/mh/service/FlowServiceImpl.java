package com.mh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mh.exception.FlowNotFoundException;
import com.mh.model.Flow;
import com.mh.repository.FlowRepository;

@Service
@Transactional(rollbackFor = { FlowNotFoundException.class })
public class FlowServiceImpl implements FlowService {

	@Autowired
	private FlowRepository flowRepository;

	@Override
	public Flow create(Flow flow) {
		return flowRepository.save(flow);
	}

	@Override
	public Flow get(Integer id) throws FlowNotFoundException {
		Flow flow = null;
		if (id instanceof Integer)
			flow = flowRepository.findOne(id);
		if (flow != null)
			return flow;
		throw new FlowNotFoundException(id);
	}

	@Override
	public List<Flow> getAll() {
		return flowRepository.findAll();
	}

	@Override
	public Flow update(Flow flow) {
		Flow sFlowToUpdate = get(flow.getId());
		sFlowToUpdate.update(flow);
		return sFlowToUpdate;
	}

	@Override
	public Flow delete(Integer id) {
		Flow sFlow = get(id);
		flowRepository.delete(id);
		return sFlow;
	}

	@Override
	public Flow like(Integer id) {
		Flow sFlow = get(id);
		sFlow.setLikes(sFlow.getLikes() + 1);
		sFlow.update(sFlow);
		return sFlow;
	}

}
