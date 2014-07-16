package com.mh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mh.model.Relation;

public interface RelationRepository extends JpaRepository<Relation, Integer> {

}
