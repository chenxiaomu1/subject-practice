package com.chenhan.service;

import com.chenhan.entity.User;

public interface UserService {
	
	public User login(User user);
	
	public User logout(User user);
	
	public User getUserInfo(User user);
	
}
