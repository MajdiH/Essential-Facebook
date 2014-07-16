package com.mh.service;

import java.util.List;

import com.mh.exception.RelationNotFoundException;
import com.mh.model.Relation;

public interface RelationService {
	public Relation create(Relation relation);
	public Relation get(Integer id) throws RelationNotFoundException;
	public List<Relation> getAll();
	public Relation update(Relation relation);
	public Relation delete(Integer id);
	public Relation enable(Integer id);
}
