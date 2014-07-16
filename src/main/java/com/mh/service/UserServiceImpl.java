package com.mh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mh.exception.UserNotFoundException;
import com.mh.model.User;
import com.mh.repository.UserRepository;

@Service
@Transactional(rollbackFor = { UserNotFoundException.class })
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public User get(Integer id) throws UserNotFoundException {
		User user = null;
		if (id instanceof Integer)
			user = userRepository.findOne(id);
		if (user != null)
			return user;
		throw new UserNotFoundException(id);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User update(User user) {
		User sUserToUpdate = get(user.getId());
		sUserToUpdate.update(user);
		return sUserToUpdate;
	}

	@Override
	public User delete(Integer id) {
		User sUser = get(id);
		userRepository.delete(id);
		return sUser;
	}

}
