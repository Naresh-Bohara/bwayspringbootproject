package com.bway.springbootproject.service;

import com.bway.springbootproject.model.User;

public interface UserService {
	void registerUser(User u);
	User loginUser(String un, String pass);
}
