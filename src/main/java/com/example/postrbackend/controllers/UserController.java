package com.example.postrbackend.controllers;

import com.example.postrbackend.data.entities.User;
import com.example.postrbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("users")
	public ResponseEntity<List<User>> getUserList(){
		return new ResponseEntity<List<User>>(
				userService.getUserList(),
				HttpStatus.OK);
	}
	@GetMapping("user")
	public ResponseEntity<User> getUser(
			Authentication authentication){
		return new ResponseEntity(
				userService.getUser(authentication),
				HttpStatus.OK);
	}

	@PostMapping("user")
	public ResponseEntity<User> addUser(
			Authentication authentication,
			@Valid @RequestBody User userRequest){
			return new ResponseEntity<>(
					userService.addUser(authentication,
							userRequest),
					HttpStatus.OK);
	}

	@PutMapping("user")
	public ResponseEntity<User> editUser(
			Authentication authentication,
			@Valid @RequestBody User userRequest){
		return new ResponseEntity(
				userService.editUser(authentication,userRequest)
			,HttpStatus.OK);
	}
}

