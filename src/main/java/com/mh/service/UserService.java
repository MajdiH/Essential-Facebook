package com.mh.service;

import java.util.List;

import com.mh.exception.UserNotFoundException;
import com.mh.model.User;

public interface UserService {
	public User create(User user);
	public User get(Integer id) throws UserNotFoundException;
	public List<User> getAll();
	public User update(User user);
	public User delete(Integer id);
}
