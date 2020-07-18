package com.example.postrbackend.services.implementations;

import com.example.postrbackend.data.entities.Follow;
import com.example.postrbackend.data.repositories.FollowedRepository;
import com.example.postrbackend.data.repositories.UserRepository;
import com.example.postrbackend.services.FollowService;
import com.example.postrbackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements Utils, FollowService {

	@Autowired
	private FollowedRepository followedRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Follow> getFollows(
			Authentication authentication){
		Integer userId = userRepository.findByFirebaseUid(extractUser(authentication,userRepository).getFirebaseUid()).getUserId();
		return followedRepository
				.findAll()
				.stream()
				.filter(
				message -> message.getUser().getUserId().equals(userId))
				.collect(Collectors.toList());
	}

	@Override
	public Follow addFollow(
			Authentication authentication,
			Follow follow){
		follow.setUser(extractUser(authentication,userRepository));
		return followedRepository.save(follow);
	}

	@Override
	public void delFollow(
			Authentication authentication,
			Integer eventId){
		followedRepository.deleteByIds(extractUser(authentication,userRepository).getUserId(),eventId);
	}
}
