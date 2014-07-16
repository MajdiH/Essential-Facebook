package com.mh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mh.exception.MessageNotFoundException;
import com.mh.model.Message;
import com.mh.repository.MessageRepository;

@Service
@Transactional(rollbackFor = { MessageNotFoundException.class })
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public Message create(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public Message get(Integer id) throws MessageNotFoundException {
		Message message = null;
		if (id instanceof Integer)
			message = messageRepository.findOne(id);
		if (message != null)
			return message;
		throw new MessageNotFoundException(id);
	}

	@Override
	public List<Message> getAll() {
		return messageRepository.findAll();
	}

	@Override
	public Message update(Message message) {
		Message sMessageToUpdate = get(message.getId());
		sMessageToUpdate.update(message);
		return sMessageToUpdate;
	}

	@Override
	public Message delete(Integer id) {
		Message sMessage = get(id);
		messageRepository.delete(id);
		return sMessage;
	}

}
