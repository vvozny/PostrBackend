package com.example.postrbackend.services.implementations;

import com.example.postrbackend.data.entities.Comment;
import com.example.postrbackend.data.entities.Follow;
import com.example.postrbackend.data.repositories.CommentRepository;
import com.example.postrbackend.data.repositories.FollowedRepository;
import com.example.postrbackend.data.repositories.UserRepository;
import com.example.postrbackend.data.entities.PushNotificationRequest;
import com.example.postrbackend.services.PushNotificationService;
import com.example.postrbackend.services.CommentService;
import com.example.postrbackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements Utils, CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FollowedRepository followedRepository;
	@Autowired
	private PushNotificationService pushNotificationService;

	public List<Comment> getComments( Integer eventId){
		return commentRepository.findAll().stream().filter(comment -> comment.getEventId().equals(eventId)).collect(Collectors.toList());
	}

	public Comment addComment(Authentication authentication, Comment comment){
		comment.setUser(extractUser(authentication,userRepository));
		comment.setCreationTimestamp(getCurrentTimestamp());
		List<Follow> followList = followedRepository
				.findAllByEventEventId(comment.getEventId());
		for(Follow follow : followList){
			pushNotificationService.sendPushNotificationToToken(
					new PushNotificationRequest(
							comment.getUser().getName()+" commented on "
									+follow.getEvent().getEventName(),
							comment.getContent(),
							"Comment",
							follow.getUser().getFirebaseRegToken()));
		}
		return commentRepository.save(comment);
	}

}
