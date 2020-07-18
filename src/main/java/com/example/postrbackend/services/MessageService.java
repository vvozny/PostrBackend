package com.example.postrbackend.services;

import com.example.postrbackend.data.entities.Message;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface MessageService {
	public List<Message> getMessageAuthors(Authentication authentication);
	public List<Message> getMessages(Authentication authentication,
	                                 Integer userId2,
	                                 Integer messsageId) ;

	public List<Message> getMessagesBetweenUsers(Authentication authentication,
	                                             Integer userId2) ;

	public Message addMessage(Authentication authentication, Message message);
}
