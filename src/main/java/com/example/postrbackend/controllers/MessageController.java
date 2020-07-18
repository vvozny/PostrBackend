package com.example.postrbackend.controllers;

import com.example.postrbackend.data.entities.Message;
import com.example.postrbackend.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class MessageController {
	private final MessageService messageService;

	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@GetMapping("authors")
	public ResponseEntity<List<Message>> getMessageAuthors(Authentication authentication){
		return  new ResponseEntity<>(messageService.getMessageAuthors(authentication),HttpStatus.OK);
	}

	@GetMapping("messages/aft/{uid2}/{messId}")
	public ResponseEntity<List<Message>> getMessages(Authentication authentication,
	                                 @PathVariable(value = "uid2") Integer userId2,
	                                 @PathVariable(value = "messId") Integer messsageId){
		return new ResponseEntity<>(messageService.getMessages(authentication,userId2,messsageId), HttpStatus.OK);

	}

	@GetMapping("messages/{uid2}")
	public ResponseEntity<List<Message>> getMessagesBetweenUsers(Authentication authentication,
	                                             @PathVariable(value = "uid2") Integer userId2){
		return new ResponseEntity<>(messageService.getMessagesBetweenUsers(authentication,userId2),HttpStatus.OK);
	}


	@PostMapping("message")
	public ResponseEntity<Message> addMessage(Authentication authentication,
			@Valid @RequestBody Message message){
		return new ResponseEntity<>(messageService.addMessage(authentication,message),HttpStatus.OK);
	}


}