package com.example.postrbackend.services.implementations;

import com.example.postrbackend.data.entities.PushNotificationRequest;
import com.example.postrbackend.services.FCMService;
import com.example.postrbackend.services.PushNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PushNotificationServiceImpl implements PushNotificationService{


		private Logger logger = LoggerFactory.getLogger(com.example.postrbackend.services.PushNotificationService.class);
		private FCMService fcmService;

		public PushNotificationServiceImpl(FCMService fcmService) {
			this.fcmService = fcmService;
		}

		public void sendPushNotificationToToken(PushNotificationRequest request) {
			try {
				fcmService.sendMessageToToken(request);
			} catch (InterruptedException | ExecutionException e) {
				logger.error(e.getMessage());
			}
		}
	}

