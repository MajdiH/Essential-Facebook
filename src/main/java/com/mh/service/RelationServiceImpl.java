package com.mh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mh.exception.RelationNotFoundException;
import com.mh.model.Relation;
import com.mh.repository.RelationRepository;

@Service
@Transactional(rollbackFor = { RelationNotFoundException.class })
public class RelationServiceImpl implements RelationService {

	@Autowired
	private RelationRepository relationRepository;

	@Override
	public Relation create(Relation relation) {
		return relationRepository.save(relation);
	}

	@Override
	public Relation get(Integer id) throws RelationNotFoundException {
		Relation relation = null;
		if (id instanceof Integer)
			relation = relationRepository.findOne(id);
		if (relation != null)
			return relation;
		throw new RelationNotFoundException(id);
	}

	@Override
	public List<Relation> getAll() {
		return relationRepository.findAll();
	}

	@Override
	public Relation update(Relation relation) {
		Relation sRelationToUpdate = get(relation.getId());
		sRelationToUpdate.update(relation);
		return sRelationToUpdate;
	}

	@Override
	public Relation delete(Integer id) {
		Relation sRelation = get(id);
		relationRepository.delete(id);
		return sRelation;
	}

	@Override
	public Relation enable(Integer id) {
		Relation sRelation = get(id);
		System.out.println(sRelation);
		sRelation.setEnabled(!sRelation.getEnabled());
		sRelation.update(sRelation);
		return sRelation;
	}

}
