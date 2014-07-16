package com.mh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mh.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
