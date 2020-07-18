package com.example.postrbackend.controllers;

import com.example.postrbackend.data.entities.Comment;
import com.example.postrbackend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

	private final CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService){
		this.commentService = commentService;
	}

	@GetMapping("comments/{id}")
	public ResponseEntity<List<Comment>> getComments(@PathVariable(value = "id") Integer eventId){
		return new ResponseEntity<>(commentService.getComments(eventId),HttpStatus.OK);
	}

	@PostMapping("comment")
	public ResponseEntity<Comment> addComment(Authentication authentication, @Valid @RequestBody Comment comment){
		return new ResponseEntity<>(commentService.addComment(authentication,comment),HttpStatus.OK);
	}
}
