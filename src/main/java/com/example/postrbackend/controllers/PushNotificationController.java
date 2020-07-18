package com.example.postrbackend.controllers;


import com.example.postrbackend.data.entities.PushNotificationRequest;
import com.example.postrbackend.services.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PushNotificationController {

	private final PushNotificationService pushNotificationService;

	@Autowired
	public PushNotificationController(PushNotificationService pushNotificationService) {
		this.pushNotificationService = pushNotificationService;
	}

	@PostMapping("/notification/token")
	public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
		pushNotificationService.sendPushNotificationToToken(request);
		return new ResponseEntity<>("Notification has been sent.", HttpStatus.OK);
	}
}
