package com.example.postrbackend.controllers;

import com.example.postrbackend.data.entities.Follow;
import com.example.postrbackend.services.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class FollowController {

	private FollowService followService;

	@Autowired
	public FollowController(FollowService followService){
		this.followService = followService;
	}

	@GetMapping("follows")
	public ResponseEntity<List<Follow>> getFollows(Authentication authentication){
		return new ResponseEntity<>(followService.getFollows(authentication),HttpStatus.OK);
	}

	@PostMapping("follow")
	public ResponseEntity<Follow> addFollow(Authentication authentication,@Valid @RequestBody Follow follow){
		return new ResponseEntity<>(followService.addFollow(authentication,follow),HttpStatus.OK);
	}

	@DeleteMapping("follow/{id}")
	public ResponseEntity<Follow> delFollow(Authentication authentication,@PathVariable(value = "id") Integer eventId){
		followService.delFollow(authentication,eventId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}