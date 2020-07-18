package com.example.postrbackend.services.implementations;

import com.example.postrbackend.data.entities.Message;
import com.example.postrbackend.data.entities.User;
import com.example.postrbackend.data.repositories.MessageRepository;
import com.example.postrbackend.data.repositories.UserRepository;
import com.example.postrbackend.data.entities.PushNotificationRequest;
import com.example.postrbackend.services.PushNotificationService;
import com.example.postrbackend.services.MessageService;
import com.example.postrbackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements Utils, MessageService {

	@Autowired
	private PushNotificationService pushNotificationService;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Message> getMessageAuthors(Authentication authentication) {
		Integer userId = extractUser(authentication,userRepository).getUserId();
		List<User> authors = userRepository.findAllAuthors(userId);
		ArrayList<Message> lastMessagesFromAuthors = new ArrayList<Message>();
		for (User user : authors) {
			lastMessagesFromAuthors.add(messageRepository.findLastMessage(userId, user.getUserId()));
		}
		return ((List<Message>) lastMessagesFromAuthors);
	}

	@Override
	public List<Message> getMessages(Authentication authentication,
	                                 Integer userId2,
	                                 Integer messsageId) {
		Integer userId1 = extractUser(authentication,userRepository).getUserId();

		return messageRepository
				.findBetweenUsers(userId1, userId2, messsageId);
	}

	@Override
	public List<Message> getMessagesBetweenUsers(Authentication authentication,
	                                             Integer userId2) {
		Integer userId1 = extractUser(authentication,userRepository).getUserId();
		return messageRepository.
				findBetweenUsers(userId1, userId2);
	}

	@Override
	public Message addMessage(Authentication authentication, Message message) {
		message.setUser1(extractUser(authentication,userRepository));
		message.setStatusId(1);
		message.setSendTimestamp(getCurrentTimestamp());
		pushNotificationService.sendPushNotificationToToken(new PushNotificationRequest(
				"Message from " + message.getUser1().getName(),
				message.getContent(),
				"Message",
				message.getUser2().getFirebaseRegToken()));
		return messageRepository.save(message);
	}


}
