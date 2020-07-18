package com.example.postrbackend.services;

import com.example.postrbackend.data.entities.Follow;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface FollowService {

	public List<Follow> getFollows(
			Authentication authentication);

	public Follow addFollow(
			Authentication authentication,
			Follow follow);

	public void delFollow(
			Authentication authentication,
			Integer eventId);
}
