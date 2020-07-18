package com.example.postrbackend.services;

import com.example.postrbackend.data.entities.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

	public List<User> getUserList();
	public User getUser(Authentication authentication);
	public User addUser(Authentication authentication, User userRequest);
	public User editUser(Authentication authentication, User userRequest);
}
