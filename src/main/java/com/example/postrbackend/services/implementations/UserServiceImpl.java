package com.example.postrbackend.services.implementations;

import com.example.postrbackend.data.entities.User;
import com.example.postrbackend.data.repositories.UserRepository;
import com.example.postrbackend.services.UserService;
import com.example.postrbackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService,Utils {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getUserList(){
		return userRepository.findAll();
	}

	@Override
	public User getUser(Authentication authentication){
		return extractUser(authentication,userRepository);
	}

	@Override
	public User addUser(Authentication authentication, User userRequest){
		if ((userRepository.findAll().stream().noneMatch(user ->
				user.getEmail().equals(userRequest.getEmail())))) {
			return userRepository.save(userRequest);
		} else {
			return null;
		}
	}

	@Override
	public User editUser(Authentication authentication, User userRequest){
		User currentUser = userRepository
				.findByFirebaseUid(
						extractUser(authentication
								,userRepository)
								.getFirebaseUid());
		currentUser.setName(userRequest.getName());
		currentUser.setUserLatitude(userRequest.getUserLatitude());
		currentUser.setUserLongitude(userRequest.getUserLongitude());
		currentUser.setRange(userRequest.getRange());
		currentUser.setFirebaseRegToken(userRequest.getFirebaseRegToken());
		return userRepository.save(currentUser);
	}
}
