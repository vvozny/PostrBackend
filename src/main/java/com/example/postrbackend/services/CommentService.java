package com.example.postrbackend.services;

import com.example.postrbackend.data.entities.Comment;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CommentService {
	public List<Comment> getComments(
			Integer eventId);
	public Comment addComment(
			Authentication authentication,
			Comment comment);
}