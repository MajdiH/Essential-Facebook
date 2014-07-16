package com.mh.service;

import java.util.List;

import com.mh.exception.MessageNotFoundException;
import com.mh.model.Message;

public interface MessageService {
	public Message create(Message message);
	public Message get(Integer id) throws MessageNotFoundException;
	public List<Message> getAll();
	public Message update(Message message);
	public Message delete(Integer id);
}
