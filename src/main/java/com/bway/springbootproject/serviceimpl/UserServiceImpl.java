package com.bway.springbootproject.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springbootproject.model.User;
import com.bway.springbootproject.repository.UserRepository;
import com.bway.springbootproject.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public void registerUser(User u) {
		
		if(userRepo.findByUsername(u.getUsername()) != null) {
			throw new RuntimeException("Username already exists");
		}
		
		userRepo.save(u);
	}

	@Override
	public User loginUser(String un, String pass) {
		
		return userRepo.findByUsernameAndPassword(un, pass);
	}

}
